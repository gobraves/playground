package Todo.model;

import javafx.beans.property.*;

import java.time.LocalDate;

/**
 * Created by simple_chen on 15-6-7.
 */

public class Plan {
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty content = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<LocalDate>();
    private final BooleanProperty state = new SimpleBooleanProperty();



    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public Boolean getState() {
        return state.get();
    }

    public BooleanProperty stateProperty() { return state ; }

    public void setState(Boolean state) {
        this.state.set(state);
    }





    public String getcontent() {
        return content.get();
    }

    public StringProperty contentProperty() {
        return content;
    }

    public void setcontent(String content) {
        this.content.set(content);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public  Plan(String title, String content) {
        this.setTitle(title);
        this.setcontent(content);

    }

    public Plan() {

    }



}

