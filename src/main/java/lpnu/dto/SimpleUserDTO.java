package lpnu.dto;

public class SimpleUserDTO {
    private String name;

    public SimpleUserDTO() {
    }

    public SimpleUserDTO(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
