package vtb.geekbrains;

@Table(title = "students")
public class Students {
    @Column
    private Integer id;

    @Column
    private String name;

    @Column
    private Integer score;
}
