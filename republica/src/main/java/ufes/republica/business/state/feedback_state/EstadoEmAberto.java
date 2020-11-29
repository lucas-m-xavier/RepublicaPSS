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
public class EstadoEmAberto extends FeedbackState{

    public EstadoEmAberto(Feedback feedback) {
        super(feedback);
    }
    
    @Override
    public void concluirFeedback() {
        this.getFeedback().setEstado(new EstadoConcluido(this.getFeedback()));
    }
}
