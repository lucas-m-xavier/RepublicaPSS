/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.business.state.feedback_state;

import ufes.republica.model.Feedback;

/**
 *
 * @author Lucas
 */
public abstract class FeedbackState {
    private Feedback feedback;
    
    public FeedbackState(Feedback feedback) {
        this.feedback = feedback;
    }
    
    public void concluirFeedback() {
        throw new RuntimeException("Não é possivel executar a operação concluirFeedback!");
    }

    public Feedback getFeedback() {
        return feedback;
    }
}
