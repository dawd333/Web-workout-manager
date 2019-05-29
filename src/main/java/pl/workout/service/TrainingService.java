package pl.workout.service;

import org.springframework.stereotype.Service;
import pl.workout.model.Training;
import pl.workout.model.User;
import pl.workout.payload.TrainingCreateRequest;
import pl.workout.payload.TrainingUpdateRequest;
import pl.workout.repository.TrainingRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TrainingService {

    private TrainingRepository trainingRepository;

    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public List<Training> getAllByUser(User user) {
        return trainingRepository.getAllByUser(user);
    }

    public Training getById(Long id) {
        return trainingRepository.getById(id);
    }

    public Training createTraining(User user, TrainingCreateRequest trainingCreateRequest) {
        System.out.println(trainingCreateRequest.getDate());
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-mm-yyyy").parse(trainingCreateRequest.getDate());
        } catch(ParseException e) {
            e.printStackTrace();
        }
        return trainingRepository.save(new Training(user, date));
    }

    public Training updateTraining(Long id, TrainingUpdateRequest trainingUpdateRequest) {
        Training training = getById(id);
        training.setDate(trainingUpdateRequest.getDate());
        training.setExerciseList(trainingUpdateRequest.getExerciseList());
        return trainingRepository.save(training);
    }

    public void deleteTraining(Long id) {
        trainingRepository.deleteById(id);
    }
}
