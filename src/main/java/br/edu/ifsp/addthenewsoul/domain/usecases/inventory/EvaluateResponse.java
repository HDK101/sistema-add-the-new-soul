package br.edu.ifsp.addthenewsoul.domain.usecases.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.LocationStatus;
import lombok.Builder;

@Builder
public class EvaluateResponse {
    private boolean damageApplied;
    private LocationStatus currentLocationStatus;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EvaluateResponse{");
        sb.append("damageApplied=").append(damageApplied);
        sb.append(", currentLocationStatus=").append(currentLocationStatus);
        sb.append('}');
        return sb.toString();
    }
}
