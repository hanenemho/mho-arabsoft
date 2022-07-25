package org.sid.catservice.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.sid.catservice.dao.ContribuableDao;
import org.sid.catservice.dao.DetailsFactureDao;
import org.sid.catservice.dao.FactureDao;
import org.sid.catservice.dao.SubTotauxFactureDao;
import org.sid.catservice.dto.RechercheFactDto;
import org.sid.catservice.entities.DetailsFacture;
import org.sid.catservice.entities.Facture;
import org.sid.catservice.entities.FactureState;
import org.sid.catservice.entities.SubTotauxFacture;
import org.sid.catservice.entities.SubTotauxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactureService extends GenericService<Facture, FactureDao>implements FactureIService {
		
		@Autowired
		private FactureDao factureDao;
		@Autowired
		private ContribuableDao contribuableDao;
		
		@Autowired
		private DetailsFactureDao detailsFactureDao;
				
		@Autowired
		private SubTotauxService SubTotauxService;
		
		@Autowired
		private ClientService clientService;
		
		@Autowired
		private ParamProduitService paramproduitService;
		
		@Autowired
		private SubTotauxFactureDao subTotauxFactureDao;
		
		@Autowired
		private CommunicationCMF comCmf;

		@Override
		public SubTotauxResponse addFacture(Facture facture) {
			 SubTotauxResponse sbr = new SubTotauxResponse();
		     Float montantTaxSp=new Float(0);
		     Float montantTotals=new Float(0);
		     System.out.println("test : :"+this.factureDao.existsByNumFact(facture.getNumFact()));
	         if(this.factureDao.existsByNumFact(facture.getNumFact())==false) {
		          System.out.println("test : : 1");

			    if (this.clientService.existingClient(facture.getClient().getMatriculeFiscaleClient())==false){				
				  this.clientService.addClient(facture.getClient());
			      }
				  facture.setClient(facture.getClient());
				  facture.setPointVente(facture.getPointVente());
				  facture.setContribuable(facture.getContribuable());
				  facture.setSaved(true);
				  facture.setFactureAvoir(facture.getFactureAvoir());
				  this.factureDao.save(facture);
			 

			 for (DetailsFacture dt: facture.getDetailsFacture()) {
				 dt.setFacture(facture);
				 this.paramproduitService.addParamProduit(dt.getParamProduit());
				 dt.setParamProduit(dt.getParamProduit());
				 dt.setGroupeTaxation(dt.getGroupeTaxation());	
				 if (dt.getTaxSpec()!=null)
				   montantTaxSp+=Float.parseFloat(dt.getTaxSpec());
		          
				  this.detailsFactureDao.save(dt) ;
				  
		          SubTotauxFacture sb=new SubTotauxFacture();
		          Float montantHT=Float.parseFloat(dt.getParamProduit().getPrixUnitaire())*Float.parseFloat(dt.getQuantite());
		          Float montantTTC=montantHT+
		        		  (montantHT*Float.parseFloat(dt.getGroupeTaxation().getTaxTva())/100);
		          
		          sb.setCodeGrp(dt.getGroupeTaxation().getCodeGrpTax());
		          //sb.setStfId(RandomStringUtils.random(10, true, true));
		          sb.setTotalHt(montantHT.toString());		          
                  sb.setTotalTax(montantTTC.toString());
                  sb.setFacture(facture);
                  
		          if (sbr.getSubTotauxFactureList().isEmpty()) {
		        	  sbr.getSubTotauxFactureList().add(sb);
		          }else if(findIndexByCode(sbr.getSubTotauxFactureList(),sb)==-1){
		        	  sbr.getSubTotauxFactureList().add(sb);	 
		          }else {
			        Float updatedMontantHt=
			        Float.parseFloat(sbr.getSubTotauxFactureList().get(findIndexByCode(sbr.getSubTotauxFactureList(),sb)).getTotalHt())+
			        		montantHT;
			        Float updatedMontanTTC=
					        Float.parseFloat(sbr.getSubTotauxFactureList().get(findIndexByCode(sbr.getSubTotauxFactureList(),sb)).getTotalTax())+
					        		montantTTC;
			        
			        sbr.getSubTotauxFactureList().get(findIndexByCode(sbr.getSubTotauxFactureList(),sb)).setTotalHt(updatedMontantHt.toString());
			        sbr.getSubTotauxFactureList().get(findIndexByCode(sbr.getSubTotauxFactureList(),sb)).setTotalTax(updatedMontanTTC.toString());
		          }
			 }
			 facture.setMontantTaxSpec(montantTaxSp.toString());
			 sbr.setMontantTaxSpec(montantTaxSp.toString());
			 this.factureDao.save(facture);
			 montantTotals+=montantTaxSp;
			 for(int i=0;i<sbr.getSubTotauxFactureList().size();i++) {
			     System.out.println("test : : aq"+sbr.getSubTotauxFactureList().get(i).getCodeGrp());

				this.SubTotauxService.addSubTotauxFacture(sbr.getSubTotauxFactureList().get(i));
				montantTotals+=Float.parseFloat(sbr.getSubTotauxFactureList().get(i).getTotalHt())
						+Float.parseFloat(sbr.getSubTotauxFactureList().get(i).getTotalTax());
			 }
			 sbr.setMontantTotal(montantTotals.toString());
		 } else { 
		     System.out.println("test : : 2");

				  facture.setClient(facture.getClient());
				  facture.setPointVente(facture.getPointVente());
				  facture.setContribuable(facture.getContribuable());
				  facture.setSaved(true);
				  this.factureDao.save(facture);
			 for (DetailsFacture dt: facture.getDetailsFacture()) {
				 dt.setFacture(facture);
				 dt.setParamProduit(dt.getParamProduit());
				 dt.setGroupeTaxation(dt.getGroupeTaxation());				 
		          this.detailsFactureDao.save(dt) ;
		          if (dt.getTaxSpec()!=null)
						 montantTaxSp+=Float.parseFloat(dt.getTaxSpec());
		          SubTotauxFacture sb=new SubTotauxFacture();
		          Float montantHT=Float.parseFloat(dt.getParamProduit().getPrixUnitaire())*Float.parseFloat(dt.getQuantite());
		          Float montantTTC=montantHT+
		        		  (montantHT*Float.parseFloat(dt.getGroupeTaxation().getTaxTva())/100);
		          sb.setCodeGrp(dt.getGroupeTaxation().getCodeGrpTax());
		          //sb.setStfId(RandomStringUtils.random(10, true, true));
		          sb.setTotalHt(montantHT.toString());		          
                 sb.setTotalTax(montantTTC.toString());
                 sb.setFacture(facture);
		          if (sbr.getSubTotauxFactureList().isEmpty()) {
		        	  sbr.getSubTotauxFactureList().add(sb);
		          }else if(findIndexByCode(sbr.getSubTotauxFactureList(),sb)==-1){
		        	  sbr.getSubTotauxFactureList().add(sb);	 
		          }else {
			        Float updatedMontantHt=
			        Float.parseFloat(sbr.getSubTotauxFactureList().get(findIndexByCode(sbr.getSubTotauxFactureList(),sb)).getTotalHt())+
			        		montantHT;
			        Float updatedMontanTTC=
					        Float.parseFloat(sbr.getSubTotauxFactureList().get(findIndexByCode(sbr.getSubTotauxFactureList(),sb)).getTotalTax())+
					        		montantTTC;
			        sbr.getSubTotauxFactureList().get(findIndexByCode(sbr.getSubTotauxFactureList(),sb)).setTotalHt(updatedMontantHt.toString());
			        sbr.getSubTotauxFactureList().get(findIndexByCode(sbr.getSubTotauxFactureList(),sb)).setTotalTax(updatedMontanTTC.toString());
		          }
			 }
			 facture.setMontantTaxSpec(montantTaxSp.toString());
			 sbr.setMontantTaxSpec(montantTaxSp.toString());
			 this.factureDao.save(facture);
		       	this.subTotauxFactureDao.deleteAllFactures(facture.getId());
				 montantTotals+=montantTaxSp;

		       for(int i=0;i<sbr.getSubTotauxFactureList().size();i++) {
				this.SubTotauxService.addSubTotauxFacture(sbr.getSubTotauxFactureList().get(i));
			montantTotals+=Float.parseFloat(sbr.getSubTotauxFactureList().get(i).getTotalHt())
					+Float.parseFloat(sbr.getSubTotauxFactureList().get(i).getTotalTax());
			 }
			   sbr.setMontantTotal(montantTotals.toString());
		 }
 			return sbr;
			
			
		}
		
		@Override
		public List<Facture> getAllFacture() {
			// TODO Auto-generated method stub
			return this.factureDao.findAll();
		}

		/*
		 * @Override 
		 * public List<Facture> getAllFactureByObj(String obj) {
		 * 
		 * System.out.print("obj obj"+obj); return this.factureDao.findByNumFact(obj); }
		 */
		@Override 
		 public List<Facture> getFactureByCodeAgent(String codeAgent) {
		  
			return this.factureDao.getFactureByCodeAgent(codeAgent); 
			}
		
		@Override 
		 public List<Facture> getAllFactureByCodeAgent(String codeAgent) {
		  
			return this.factureDao.getAllFactureByCodeAgent(codeAgent); 
			}
		
		
		
		@Override
		public Boolean validateFacture(String numFacture) {
			Facture fct =this.factureDao.findByNumFact(numFacture).get(0);
			if (fct!=null) {
				fct.setValid(true);
				/* information de client a envoyer a mcf*/
				fct.setInfoClient(clientInfo(fct));
				fct.setInfoClientHEX(this.comCmf.prepareCommande("c3", clientInfo(fct)));
				fct.setInfoFacture(factInfo(fct));
				fct.setInfoFactureHEX(this.comCmf.prepareCommande("c0", factInfo(fct)));
				fct.setState(FactureState.VALIDER);
				for (DetailsFacture dt: fct.getDetailsFacture()) {
					dt.setInfoDetFact(detailfactInfo(dt));
					dt.setInfoDetFactHEX(this.comCmf.prepareCommande("31", detailfactInfo(dt)));
					this.detailsFactureDao.save(dt) ;
				}
				this.factureDao.save(fct); 
				
				return true;
			}else {			
				return false;
			}
		}
		
		@Override
		public Facture getFactureByNumFact(String numFact) {
			return this.factureDao.findByNumFact(numFact).get(0);
		}
		
		@Override
		public Boolean deleteFacture(Long id) {			
			this.SubTotauxService.deleteAllFactures(id);
			this.detailsFactureDao.deleteDetailsFactures(id);
			delete(id);
				return true;
			
		}
		
		 public int findIndexByCode(ArrayList<SubTotauxFacture> SubTotauxFactList,SubTotauxFacture dt ) {
	        int index=-1;
	        for(int i=0;i<SubTotauxFactList.size();i++) {
	        	System.out.println("SubTotauxFactList.get(i).getCodeGrp() : :"+SubTotauxFactList.get(i).getCodeGrp());
	        	System.out.println("Sdt.getCodeGrp() : :"+dt.getCodeGrp());

	        	if(SubTotauxFactList.get(i).getCodeGrp().contains(dt.getCodeGrp())) {
	       			index=i;
		        	System.out.println("index : :"+index);

	       			
	       		         break;
	       		 		}      		  
	        		}
					return index;
	        }

		@Override
		public List<Facture> rechercheFacture(RechercheFactDto rechercheFact) {
				 return this.factureDao.rechercheFacture(rechercheFact);
				 
		}

		@Override
		public List<Long> countFactByMonth() {
			List<Long> result = new ArrayList<>();
			int[] months = {1,2,3,4,5,6,7,8,9,10,11,12};
			for (int i :months) {
				result.add(factureDao.getNbFactByMonth(i));
			}
			return result;
		}

		@Override
		public String clientInfo(Facture fact) {
			String Ci = fact.getClient().getMatriculeFiscaleClient()+","+
						fact.getClient().getNom()+","+
						fact.getClient().getAddresse()+","+
						fact.getClient().getNumTel()+","+
						fact.getClient().getEmail();
			return Ci;
		}
		
		@Override
		public String factInfo(Facture fact) {
			String fi = fact.getPointVente().getId()+","+
						fact.getPointVente().getNomAgent()+","+
						"9999900000002"+","+
						fact.getTypFact()+","+
						"HT"+","+
						"12345678900"+","+
						fact.getNumFact();
			return fi;
		}

		@Override
		public String detailfactInfo(DetailsFacture df) {
			String dfi = "["+df.getRefProduit()+"]"+
						 df.getLibProduit()+" "+
						 df.getGroupeTaxation().getParamPays().getLibPays()+" "+
						 df.getGroupeTaxation().getCodeGrpTax()+
						 df.getGroupeTaxation().getTaxTva()+"%"+
						 (Integer.parseInt(df.getQuantite()) * Integer.parseInt(df.getPrixUnit()))+" "+
						 Integer.parseInt(df.getQuantite())+"*"+
						 Integer.parseInt(df.getPrixUnit());
			return dfi;
		}

	
}
