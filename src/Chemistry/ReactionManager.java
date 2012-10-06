package Chemistry;

import Collision.*;

public class ReactionManager {
	public Physics <Molecule> pEngine;
	//Conditions
	
	public ReactionManager () {
		
	}
	
	/*updateConditions
	
	public void update () {
		
	}
	*/
        
	public float getConcA () {
            int numA = 0;
            for(Molecule m: pEngine.getParticles()){
                if(m.getType() == Molecule.MOLECULE_A) numA++;
            }
            return (float) (numA / 1000.0);
	}
	
	public float getConcB () {
            int numB = 0;
            for(Molecule m: pEngine.getParticles()){
                if(m.getType() == Molecule.MOLECULE_B) numB++;
            }
            return (float) (numB / 1000.0);
	}
         
        public float getConcC (){
            int numC = 0;
            for(Molecule m: pEngine.getParticles()){
                if(m.getType() == Molecule.MOLECULE_C) numC++;
            }
            return (float) (numC / 1000.0);
        }
	
	public float calcReactionQuotient () {
            return (getConcC() * getConcC() / (getConcB() * getConcA()));
	}
}

