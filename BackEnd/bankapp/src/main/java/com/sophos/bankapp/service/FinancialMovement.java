package com.sophos.bankapp.service;

public class FinancialMovement {

    public float financialMovement(float value, float balance, String movementType){

        float Result = 0;

        if (movementType.equalsIgnoreCase("credit")) {
			
			Result = balance + value ;
			
		} 
		else if (movementType.equalsIgnoreCase("debit")) {
			
			Result = balance - value ;
				
			}

		return Result;

    }
    
}
