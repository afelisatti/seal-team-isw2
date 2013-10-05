package com.correportuvida.controllers;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.correportuvida.R;
import com.correportuvida.activities.PlansListActivity;
import com.correportuvida.activities.TrainingListActivity;
import com.correportuvida.adapters.PhaseAdapter;
import com.correportuvida.adapters.VelocityAdapter;
import com.correportuvida.model.Navigator;
import com.correportuvida.model.Phase;
import com.correportuvida.model.Trainer;
import com.correportuvida.model.base.Velocity;
import com.correportuvida.model.training.Training;
import com.correportuvida.services.GoogleMapsService;

public  class RunningController extends Controller {
	
	public RunningController(Activity activity) {
		super(activity);
	}

	@Override
	public void updateView() {
		
		try {
			addButtonCancelBehaviour();
			
			String planName = (String) getActivity().getIntent().getSerializableExtra(PlansListActivity.PLAN_NAME);
			String trainingName = (String) getActivity().getIntent().getSerializableExtra(TrainingListActivity.TRAINING_NAME);
			
			Trainer trainer = Trainer.getInstance();
			trainer.setController(this);
			Training training = trainer.getTraining(planName, trainingName);
			
			Navigator navigator;
			navigator = getNavigator();
			trainer.startTraining(training, navigator);
			
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText( getActivity().getApplicationContext(), "Map not available", Toast.LENGTH_SHORT ).show();
		}
		
	}

	private Navigator getNavigator() throws Exception {
		GoogleMapsService googleMapService = new GoogleMapsService(getActivity().getBaseContext(), (FragmentActivity)getActivity(), ((FragmentActivity)getActivity()).getSupportFragmentManager(), R.id.map);
		Navigator navigator = new Navigator(googleMapService);
		return navigator;
	}
	
	private void addButtonCancelBehaviour() {
		Button buttonCancel = (Button) getActivity().findViewById(R.id.button_cancel);
		buttonCancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				goToActivity(TrainingListActivity.class);
			}
		});
	}
	
	public void notifyPositionVelocityChanged(Trainer trainer) {
		Velocity velocity = trainer.getCurrentVelocity();
		TextView valorDistancia = (TextView) getActivity().findViewById(R.id.valueDistanceTraveled);
		valorDistancia.setText((new VelocityAdapter(velocity)).toString());
	}

	public void notifyPhaseChanged(Trainer trainer) {
		Phase currentPhase = trainer.getCurrentPhase();
		TextView phaseValue = (TextView) getActivity().findViewById(R.id.valueFase);
		phaseValue.setText((new PhaseAdapter(currentPhase)).toString());
	}

	

}
