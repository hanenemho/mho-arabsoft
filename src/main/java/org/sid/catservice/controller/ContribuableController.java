package org.sid.catservice.controller;

import java.util.HashMap;
import java.util.List;

import org.sid.catservice.dao.ContribuableDao;
import org.sid.catservice.dto.MessageResponse;
import org.sid.catservice.dto.PasswordDto;
import org.sid.catservice.dto.UserDto;
import org.sid.catservice.entities.Contribuable;
import org.sid.catservice.entities.PointVente;
import org.sid.catservice.security.JwtTokenUtil;
import org.sid.catservice.service.ContribuableService;
import org.sid.catservice.service.PointVenteService;
import org.sid.catservice.util.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.bytebuddy.utility.RandomString;

@RestController
@RequestMapping(value = "/Contribuable")
@CrossOrigin
class ContribuableController {

	@Autowired
	private ContribuableDao contribuableDao;
	@Autowired
	private ContribuableService contribuableService;
	@Autowired
	private PointVenteService pointVenteService;
	@Autowired
	private EmailSender emailservice;
	@Autowired
	JwtTokenUtil jwts;

	/* Get All Users */
	@GetMapping(value = "/getall")
	public List<Contribuable> getContribuableList() {
		return this.contribuableService.getContribuableList();
	}
	/* Get All Users by matricule fiscale */
	@GetMapping(value = "/getContribuableList/{matriculeFiscale}")
	public Contribuable getAllContribuables(@PathVariable("matriculeFiscale") String matriculeFiscale) {
	
		return this.contribuableService.getAllContribuables(matriculeFiscale);
	}

	/* Register User */
	@CrossOrigin
	@PostMapping(value = "/addContribuable")
	public ResponseEntity<?> addContribuable(@RequestBody Contribuable contribuable) {
		System.out.println("test" + contribuable.getPointVente().size());
		if(contribuableDao.existsByEmail(contribuable.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Contribuable deja exist! "));
		}
		//generate id codepays + matricule fiscale
		String generatedId = (contribuable.getParamPays().getCodePays())+contribuable.getMatriculeFiscale();
		contribuable.setCode(generatedId);
		// generating pin-code
		String randomCode = RandomString.make(6);
		contribuable.setVerificationCode(randomCode);
		this.contribuableDao.save(contribuable);
		for (PointVente pv : contribuable.getPointVente()) {
			// pv.setMotPasse(passwordEncoder.encode( pv.getMotPasse()));
			pv.setContribuable(contribuable);
			//sending mail to agent
			this.pointVenteService.addPointVente(pv);
			emailservice.sendSimpleMail(pv.getEmail(),
			"this is your verification code : " + randomCode, " verification code");
		}
		// sending email
		emailservice.sendSimpleMail(contribuable.getEmail(),
		 "this is your verification code : " + randomCode, " verification code");
		contribuable.setVerificationCode("");
		
		
		return ResponseEntity.ok(contribuable);
	}

	/* Validate User */

	@CrossOrigin
	@PostMapping(value = "/validateContribuable")
	public ResponseEntity<?> validateContribuable(@RequestBody Contribuable contribuable) {

		Contribuable cont = contribuableDao.findByMatriculeFiscale(contribuable.getMatriculeFiscale());
		if (cont.getVerificationCode().equals(contribuable.getVerificationCode())) {
			cont.setVerificationCode(null);
			cont.setActif(true);
			this.contribuableDao.save(cont);
			return ResponseEntity.ok(new MessageResponse("User verified!"));
		}
		return ResponseEntity.badRequest().body(new MessageResponse("Error: Wrong verification code!"));
	}

	
	@CrossOrigin
	@PostMapping(value = "/updatePassword")
	public ResponseEntity<?> updatePassword(@RequestBody PasswordDto passwordDto) {
		PointVente pointV = this.pointVenteService.getPointVenteByCodeAgent(passwordDto.getCodePoint());
		if (pointV.getVerificationCode().equals(passwordDto.getCode())) {
			pointV.setVerificationCode(null);
			pointV.setMotPasse(passwordDto.getNewPwd());
			this.pointVenteService.addPointVente(pointV);
			return ResponseEntity.ok(new MessageResponse(" mot de passe modifier!"));
		}

		return ResponseEntity.badRequest().body(new MessageResponse("Erreur: code de vérification erronées!"));
	}

	/* Send password update code */
	@CrossOrigin
	@PostMapping(value = "/sendPwdChangeCode")
	public ResponseEntity<?> SendPwdChangeCode(@RequestBody PointVente pointVente) {
 		String randomCode = RandomString.make(6);
		pointVente.setVerificationCode(randomCode);
		PointVente pv = this.pointVenteService.getPointVenteByCodeAgent(pointVente.getCodeAgent());
		if (pv != null) {
			pointVente.setMotPasse(pv.getMotPasse());
			this.pointVenteService.addPointVente(pointVente);
 		    emailservice.sendSimpleMail(pointVente.getEmail(),"Veuillez saisir le code pin suivant " + randomCode+
		    " pour la récupération de votre compte", "Réinitialisation de mot de passes");
			return ResponseEntity.ok(new MessageResponse("mot de passe modifier!"));
		}
		return ResponseEntity.badRequest().body(new MessageResponse("Erreur: code de vérification erronées!"));
	}
	/* authentification */
	@CrossOrigin
	@PostMapping(value = "/login")
	public ResponseEntity<?> logins(@RequestBody UserDto user) {

		// authM.authenticate(new
		// UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
		// System.out.print("ccccc"+jwtRequest.getUsername());
		// final UserDetails userDetails
		// =jwtUserDetailsService.loadUserByUsername(jwtRequest.getUsername());

		PointVente pv = this.pointVenteService.getPointVenteByCodeAgent(user.getUsername());
		if (pv == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		if (!pv.getMotPasse().equals(user.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		String jwt = jwts.generateToken(user);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("jwt", jwt);
		map.put("user", pv);
		return ResponseEntity.ok(map);
	}
	
	/* change user state */
	@CrossOrigin
	@PutMapping(value="/changeState")
	public ResponseEntity<?> ChangeState(@RequestBody Contribuable contribuable){
		Contribuable con = contribuableDao.findByMatriculeFiscale(contribuable.getMatriculeFiscale());
		if(con.getActif() == true) {
			con.setActif(false);
			this.contribuableDao.save(con);
			
			 emailservice.sendSimpleMail(con.getEmail(),
			 "your account has been blocked by the admin due to some reasons",
			 " Account blocked");
			 
			ResponseEntity.ok().body(new MessageResponse("compte blocké avec succeé"));
		}else {
			con.setActif(true);
			this.contribuableDao.save(con);
			/*
			 * emailservice.sendSimpleMail(con.getEmail(),
			 * "your account has been activated by the admin ", " Account activated");
			 */
			ResponseEntity.ok().body(new MessageResponse("compte activé avec succeé"));
		}
		
		return ResponseEntity.badRequest().body(new MessageResponse("erreur d'envoi!"));
	}
	
	
	/* Update User */
	@CrossOrigin
	@PutMapping(value = "/updateContribuable/{id}")
	public Contribuable updateContribuable(@PathVariable("id") Long id,@RequestBody Contribuable contribuable) {
		contribuableService.updateContribuable(id, contribuable);
		return contribuable;
	}

}
