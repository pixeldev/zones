package perfect.zones.managers.zone;

import java.util.List;

public interface PZone extends PZoneSetup {

    /**
     * This method return all Sub-Zones
     * like any zone.
     * @return ZoneSetup list
     */
    List<ZoneSetup> getZones();

    /**
     * This method sets the Sub-Zones
     * for any Zone.
     * @param zones List of Sub-Zones
     */
    void setZones(List<ZoneSetup> zones);

    /**
     * This method adds a new Sub-Zone
     * for any Zone.
     * @param zoneSetup Any Sub-Zone
     */
    void addZone(ZoneSetup zoneSetup);

    /**
     * This method removes a Sub-Zone
     * for any Zone.
     * @param zoneSetup Any Sub-Zone
     */
    void removeZone(ZoneSetup zoneSetup);

    /**
     * This method returns if the zone
     * is default or no.
     * @return boolean
     */
    boolean isDefault();

    /**
     * This method sets if the zone will
     * be default or no.
     * @param def Default
     */
    void setDefault(boolean def);

}