package net.codejava.ws;

import java.util.ArrayList;
import java.util.List;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.request.model.PredictRequest;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.Model;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;


public class Clarifai2 {
	private final static String API_KEY = "84e8b83dcec04443bb028626213fb9c8";
	
	public static List<String> recognize(String imageUrl) {
		List<String> resultList = new ArrayList<String>();

		final ClarifaiClient client = new ClarifaiBuilder(API_KEY).buildSync();
		
		Model<Concept> generalModel = client.getDefaultModels().generalModel();
		
		if(imageUrl != null && !imageUrl.isEmpty()) {

			PredictRequest<Concept> request = generalModel.predict().withInputs(
			        ClarifaiInput.forImage(imageUrl)
			    );
			List<ClarifaiOutput<Concept>> result = request.executeSync().get();
			
			if (result != null && result.size() > 0) {
	
				for (int i = 0; i < result.size(); i++) {	
					ClarifaiOutput<Concept> clarifaiOutput = result.get(i);
					List<Concept> concepts = clarifaiOutput.data();
	
					if(concepts != null && concepts.size() > 0) {
						for (int j = 0; j < concepts.size(); j++) {
							resultList.add(concepts.get(j).name());
						}
					}
				}
			}
		}
		return resultList;
	}

}
