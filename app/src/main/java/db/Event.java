package db;

public class Event {
    private int id;
    private String name;
    private String to_time;

    public String getTo_time() {
        return to_time;
    }

    public void setTo_time(String to_time) {
        this.to_time = to_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Event(int id, String name, String to_time) {
        this.id = id;
        this.name = name;
        this.to_time = to_time;
    }
}

