package de.kulturbremen.memorize;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import de.kulturbremen.memorize.manager.QuizManager;
import de.kulturbremen.memorize.model.QuestionEntity;
import de.kulturbremen.memorize.model.QuizEntity;
import de.kulturbremen.memorize.persistence.QuestionRepository;
import de.kulturbremen.memorize.persistence.QuizRepository;


/**
 * test quiz manager - use stubbing to isolate from persistence layer
 */
public class TestQuizManager {
    QuizManager quizManager;
    QuestionRepository fakeQuestionRepository;
    QuizRepository fakeQuizRepository;
    QuizEntity testQuiz;  // no id yet
    QuestionEntity testQuestion; // no id or quizId yet

    public TestQuizManager(){
        fakeQuizRepository = mock(QuizRepository.class);
        fakeQuestionRepository = mock(QuestionRepository.class);
        quizManager = new QuizManager(fakeQuizRepository, fakeQuestionRepository);
        testQuiz = new QuizEntity("Birds");
        testQuestion = new QuestionEntity();
        testQuestion.setQuestion("a common bird");
        testQuestion.setAnswer("blackbird");
    }

    @Test
    public void getQuizzesGets(){
        // GIVEN a stubbed QuizRepository.getQuizzes()
        List<QuizEntity> addedQuizzes = new ArrayList<>();
        addedQuizzes.add(testQuiz);
        addedQuizzes.add(new QuizEntity("German"));
        when(fakeQuizRepository.getQuizzes()).thenReturn(addedQuizzes);
        // WHEN QuizManager.getQuizzes() is called
        List<QuizEntity> retrievedQuizzes = quizManager.getQuizzes();
        // THEN the quizzes should be retrieved
        assertEquals(addedQuizzes, retrievedQuizzes);
    }

    @Test
    public void getQuestionsGets(){
        // GIVEN a stubbed QuestionRepository.getQuestions()
        QuizEntity quiz = new QuizEntity("qname");
        quiz.setId((long) 1);
        List<QuestionEntity> addedQuestions = new ArrayList<>();
        addedQuestions.add(new QuestionEntity("j", "h", quiz.getId()));
        addedQuestions.add(new QuestionEntity("n", "b", quiz.getId()));
        when(fakeQuestionRepository.getQuestions(quiz)).thenReturn(addedQuestions);
        // WHEN QuizManager.getQuestions() is called
        List<QuestionEntity> retrievedQuestions = quizManager.getQuestions(quiz);
        // THEN the questions should be retrieved
        assertEquals(addedQuestions, retrievedQuestions);
    }

    @Test
    public void addNewQuiz(){
        // GIVEN a list of questions and a stubbed QuizRepository.addQuiz
        List<QuestionEntity> questions = new ArrayList<>();
        questions.add(testQuestion);
        questions.add(testQuestion);
        when(fakeQuizRepository.addQuiz(any(QuizEntity.class))).thenReturn((long) 5);
        // WHEN editQuizAndQuestions is called with a new quiz and questions
        quizManager.editQuizAndQuestions(testQuiz, questions);
        // THEN quiz is added
        verify(fakeQuizRepository).addQuiz(testQuiz);
        // THEN correct number of questions are added
        verify(fakeQuestionRepository, times(questions.size())).addQuestion(testQuestion);
    }

    @Test
    public void EditQuiz(){
        // GIVEN a list of questions and a stubbed QuizRepository.addQuiz
        List<QuestionEntity> questions = new ArrayList<>();
        questions.add(testQuestion);
        questions.add(testQuestion);
        when(fakeQuizRepository.addQuiz(any(QuizEntity.class))).thenReturn((long) 5);
        quizManager.editQuizAndQuestions(testQuiz, questions);
        // WHEN editQuizAndQuestions is called with an updated questions
        questions.add(testQuestion);
        quizManager.editQuizAndQuestions(testQuiz, questions);
        // THEN the quiz and questions are deleted
        verify(fakeQuestionRepository).deleteQuestions(testQuiz);
        verify(fakeQuizRepository).deleteQuiz(testQuiz);
        // THEN quiz and then added
        verify(fakeQuizRepository).addQuiz(testQuiz);
        // THEN correct number of questions are added
        verify(fakeQuestionRepository, times(questions.size())).addQuestion(testQuestion);
    }

    @Test
    public void DeleteQuizAndQuestions(){
        // todo
    }

}

