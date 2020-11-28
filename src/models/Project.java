package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "projects")
@NamedQueries({
    @NamedQuery(
            name = "getAllProjects",
            query = "SELECT p FROM Project AS p ORDER BY p.id DESC"
            ),
    @NamedQuery(
            name = "getProjectsCount",
            query = "SELECT COUNT(p) FROM Project AS p"
            ),
//    @NamedQuery(
//            name = "getProjectDetail",
//            query = "SELECT p FROM Project AS p WHERE id = :id"
//            ),
})
@Entity
public class Project {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", length = 225, nullable = false)
    private String title;

    @Lob
    @Column(name = "content", nullable = true)
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



}
