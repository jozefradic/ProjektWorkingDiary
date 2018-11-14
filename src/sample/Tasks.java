package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Tasks {

    private final Integer id;
    private final StringProperty description;
    private final StringProperty person;
    private final StringProperty time;
    private final StringProperty note;
    private final StringProperty proccess;
    private final StringProperty priority;
    private final StringProperty borrowBy;

    //constructor

    public Tasks(Integer id, String description, String person, String time, String note, String proccess, String priority, String borrowBy) {
        this.id = new Integer(id);
        this.description = new SimpleStringProperty(description);
        this.person = new SimpleStringProperty(person);
        this.time = new SimpleStringProperty(time);
        this.note = new SimpleStringProperty(note);
        this.proccess = new SimpleStringProperty(proccess);
        this.priority = new SimpleStringProperty(priority);
        this.borrowBy = new SimpleStringProperty(borrowBy);
    }

    //getters

    public String getDescription() {return description.get();}

    public String getPerson() {return person.get();}

    public String getTime() {return time.get();}

    public String getNote() {return note.get();}

    public String getProccess() {return proccess.get();}

    public String getPriority() {return priority.get();}

    public String getBorrowBy() {return borrowBy.get();}

    //setters

    public void setDescription(String value){
        description.set(value);
    }

    public void setPerson(String value) {
        person.set(value);
    }

    public void setTime(String value) {
        time.set(value);
    }

    public void setNote(String value) {
        note.set(value);
    }

    public void setProccess(String value) {
        proccess.set(value);
    }

    public void setPriority(String value) {
        priority.set(value);
    }

    public void setBorrowBy(String value) {
        borrowBy.set(value);
    }

    //property value

    public Integer getId() {
        return id;
    }

    public StringProperty descriptionProperty(){return description;}

    public StringProperty borrowByProperty() {return borrowBy;}

    public StringProperty timeProperty() {return time;}

    public StringProperty noteProperty() {return note;}

    public StringProperty priorityProperty() {return priority;}

    public StringProperty personProperty() {return person;}

    public StringProperty proccessProperty() {return proccess;}

}
