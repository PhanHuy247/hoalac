package com.vnpt.iot.hoalac.core.model;

/**
 * Created by thohc on 8/19/17.
 */

public class QRCodeHistoryDTO {
    private Long id;
    private Long progressId;
    private String progressName;
    private String startTime;
    private Long sectorId;
    private String sectorName;
    private Byte type;
    private Long phaseDetailId;
    private Long phaseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProgressId() {
        return progressId;
    }

    public void setProgressId(Long progressId) {
        this.progressId = progressId;
    }

    public String getProgressName() {
        return progressName;
    }

    public void setProgressName(String progressName) {
        this.progressName = progressName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Long getSectorId() {
        return sectorId;
    }

    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Long getPhaseDetailId() {
        return phaseDetailId;
    }

    public void setPhaseDetailId(Long phaseDetailId) {
        this.phaseDetailId = phaseDetailId;
    }

    public Long getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(Long phaseId) {
        this.phaseId = phaseId;
    }
}
