package entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MandatePK implements Serializable {
    private int resourceId;
    private int projectId;

    public MandatePK() {
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MandatePK mandatePK = (MandatePK) o;
        return resourceId == mandatePK.resourceId &&
                projectId == mandatePK.projectId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceId, projectId);
    }
}
