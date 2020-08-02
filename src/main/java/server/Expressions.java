package server;

import java.io.Serializable;
import java.util.List;

public class Expressions implements Serializable {
    private List<SimpleQuestion> askedQuestion;

    public Expressions(List<SimpleQuestion> askedQuestion) {
        this.askedQuestion = askedQuestion;
    }

    public static Expressions findAllQuestions(List<SimpleQuestion> questionList) {
        return new Expressions(questionList);
    }

    public List<SimpleQuestion> allQuestions(){
        return askedQuestion;
    }

    /*
    public String getTodaysDayOfWeekName(){
      return "wtorek";
    }
     */
}
