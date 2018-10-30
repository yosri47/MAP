package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Mandate implements Serializable {
    @EmbeddedId
    private MandatePK mandatepk;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private Double fee;

    private Boolean state;

    @ManyToOne
    @JoinColumn(name="projectId",referencedColumnName = "projectId",insertable = false,updatable = false)
    Project project;

    @ManyToOne
    @JoinColumn(name="resourceId",referencedColumnName = "userId",insertable = false,updatable = false)
    Ressource resource;

    public Mandate(MandatePK mandatepk, Date startDate, Date endDate, Double fee, Boolean state, Project project, Ressource resource) {
        this.mandatepk = mandatepk;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fee = fee;
        this.state = state;
        this.project = project;
        this.resource = resource;
    }

    public Mandate() {
    }

    public MandatePK getMandatepk() {
        return mandatepk;
    }

    public void setMandatepk(MandatePK mandatepk) {
        this.mandatepk = mandatepk;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Ressource getResource() {
        return resource;
    }

    public void setResource(Ressource resource) {
        this.resource = resource;
    }

}
