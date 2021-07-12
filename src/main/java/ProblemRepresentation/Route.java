package ProblemRepresentation;

import java.util.ArrayList;
import java.util.List;

public class Route implements Comparable<Route> {

    private List<Integer> nodesVisitationList;
    private List<Integer> vehicleOccupationWhenLeavesNode;
    private List<Long> timeListTheVehicleLeavesTheNode;
    private List<Request> requestAttendanceList;
    private Integer tempoExtra;
    private double occupationRate;

    public Route() {
        nodesVisitationList = new ArrayList<Integer>();
        vehicleOccupationWhenLeavesNode = new ArrayList<Integer>();
        timeListTheVehicleLeavesTheNode = new ArrayList<Long>();
        requestAttendanceList = new ArrayList<Request>();
    }

    public Route(Route route) {
        nodesVisitationList = new ArrayList<Integer>(route.getNodesVisitationList());
        vehicleOccupationWhenLeavesNode = new ArrayList<Integer>(route.getVehicleOccupationWhenLeavesNode());
        timeListTheVehicleLeavesTheNode = new ArrayList<Long>(route.getTimeListTheVehicleLeavesTheNode());
        requestAttendanceList = new ArrayList<Request>(route.getRequestAttendanceList());
        occupationRate = route.getOccupationRate();
    }

    public Route(List<Integer> nodesVisitationList,
            List<Integer> vehicleOccupationWhenLeavesNode,
            List<Long> timeListTheVehicleLeavesTheNode,
            List<Request> requestAttendanceList,
            Integer tempoExtra,
            double occupationRate) {

        this();
        this.nodesVisitationList.clear();
        this.nodesVisitationList.addAll(nodesVisitationList);

        this.vehicleOccupationWhenLeavesNode.clear();
        this.vehicleOccupationWhenLeavesNode.addAll(vehicleOccupationWhenLeavesNode);

        this.timeListTheVehicleLeavesTheNode.clear();
        this.timeListTheVehicleLeavesTheNode.addAll(timeListTheVehicleLeavesTheNode);

        this.requestAttendanceList.clear();
        this.requestAttendanceList.addAll(requestAttendanceList);
        this.tempoExtra = tempoExtra;
        this.occupationRate = occupationRate;
    }

    public List<Integer> getNodesVisitationList() {
        return nodesVisitationList;
    }

    public void setNodesVisitationList(List<Integer> nodesVisitationList) {
        this.nodesVisitationList.clear();
        this.nodesVisitationList.addAll(nodesVisitationList);
    }

    public List<Integer> getVehicleOccupationWhenLeavesNode() {
        return vehicleOccupationWhenLeavesNode;
    }

    public void setVehicleOccupationWhenLeavesNode(List<Integer> vehicleOccupationWhenLeavesNode) {
        this.vehicleOccupationWhenLeavesNode.clear();
        this.vehicleOccupationWhenLeavesNode.addAll(vehicleOccupationWhenLeavesNode);
    }

    public List<Long> getTimeListTheVehicleLeavesTheNode() {
        return timeListTheVehicleLeavesTheNode;
    }

    public void setTimeListTheVehicleLeavesTheNode(List<Long> timeListTheVehicleLeavesTheNode) {
        this.timeListTheVehicleLeavesTheNode.clear();
        this.timeListTheVehicleLeavesTheNode.addAll(timeListTheVehicleLeavesTheNode);
    }

    public List<Request> getRequestAttendanceList() {
        return requestAttendanceList;
    }

    public List<Integer> getRequestAttendanceIdsList() {
        List<Integer> requestIds = new ArrayList<>();
        for (Request request : this.requestAttendanceList) {
            requestIds.add(request.getId());
        }

        return requestIds;
    }

    public void setRequestAttendanceList(List<Request> requestAttendanceList) {
        this.requestAttendanceList.clear();
        this.requestAttendanceList.addAll(new ArrayList<Request>(requestAttendanceList));
    }

    public Integer getLastNode() {
        int lastPosition = nodesVisitationList.size() - 1;
        return nodesVisitationList.get(lastPosition);
    }

    public Integer getActualOccupation() {
        int lastPosition = vehicleOccupationWhenLeavesNode.size() - 1;
        return vehicleOccupationWhenLeavesNode.get(lastPosition);
    }

    public Long getActualMoment() {
        int lastPosition = timeListTheVehicleLeavesTheNode.size() - 1;
        return timeListTheVehicleLeavesTheNode.get(lastPosition);
    }

    public Integer getTempoExtra() {
        return tempoExtra;
    }

    public double getOccupationRate() {
        return occupationRate;
    }

    public void setExtraTime(Integer extraTime) {
        tempoExtra = extraTime;
    }

    public void setOccupationRate(double occupationRate) {
        this.occupationRate = occupationRate;
    }

    public void setDepartureTimeFromDepot(long horario) {
        timeListTheVehicleLeavesTheNode.set(0, horario);
    }

    public void calculateOccupationRate(int vehicleCapacity) {
        this.setOccupationRate(this.getVehicleOccupationWhenLeavesNode().stream()
                .mapToDouble(Integer::valueOf).average()
                .getAsDouble() / vehicleCapacity);
    }

    public void addVisitedNode(Integer visitedNode) {
        nodesVisitationList.add(visitedNode);

        int lastPosition = vehicleOccupationWhenLeavesNode.size() - 1;
        int occupation;

        if (lastPosition >= 0) {
            occupation = vehicleOccupationWhenLeavesNode.get(lastPosition);
            vehicleOccupationWhenLeavesNode.add(occupation);
        } else {
            vehicleOccupationWhenLeavesNode.add(0);
        }

        timeListTheVehicleLeavesTheNode.add((long) -1);
    }

    public void boardPassenger(Request request, Long passengerBoardTime) {
        int posicao = vehicleOccupationWhenLeavesNode.size() - 1;
        int lotacao = vehicleOccupationWhenLeavesNode.get(posicao);

        vehicleOccupationWhenLeavesNode.set(posicao, lotacao + 1);
        timeListTheVehicleLeavesTheNode.set(posicao, passengerBoardTime);

        request.setPickupTime(passengerBoardTime);
        addAttendedRequest(request);
    }

    public void leavePassenger(Request request, long passengerDeliveryTime) {
        int lastPosition = vehicleOccupationWhenLeavesNode.size() - 1;
        int vehicleOccupation = vehicleOccupationWhenLeavesNode.get(lastPosition);
        vehicleOccupationWhenLeavesNode.set(lastPosition, vehicleOccupation - 1);
        timeListTheVehicleLeavesTheNode.set(lastPosition, passengerDeliveryTime);

        int positionInAttendanceList = getRequestAttendanceList().indexOf(request);
        Request attendedRequest = getRequestAttendanceList().get(positionInAttendanceList);

        attendedRequest.setDeliveryTime(passengerDeliveryTime);
        getRequestAttendanceList().set(positionInAttendanceList, attendedRequest);
    }

    public void addAttendedRequest(Request request) {
        requestAttendanceList.add((Request) request.clone());
    }

    public void removeAttendedRequest(Request request) {
        requestAttendanceList.remove((Request) request.clone());
    }

    @Override
    public String toString() {
        String s = "";

        for (Integer v : nodesVisitationList) {
            s += v + " ";
        }
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Route && equals((Route) obj);
    }

    public boolean equals(Route route) {
        if (this == route) {
            return true;
        }

        if (route == null) {
            return false;
        }

        if (nodesVisitationList.size() != route.getNodesVisitationList().size()) {
            return false;
        }

        for (int visitedNode = 0; visitedNode < nodesVisitationList.size(); visitedNode++) {
            if (nodesVisitationList.get(visitedNode) != route.getNodesVisitationList().get(visitedNode)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {

        if (nodesVisitationList == null) {
            return -1;
        }

        int hash = 0;
        String s = "";

        for (Integer i : nodesVisitationList) {
            s += i.toString();
        }

        hash = s.hashCode();
        return this.getRequestAttendanceList().get(0).getId().hashCode();
    }

    @Override
    public int compareTo(Route route) {
        if (this.getRequestAttendanceList().size() > route.getRequestAttendanceList().size()) {
            return 1;
        }
        if (this.getRequestAttendanceList().size() < route.getRequestAttendanceList().size()) {
            return -1;
        }
        return 0;
    }

    @Override
    public Object clone() {

        List<Request> requestListClone = new ArrayList<>();

        for (Request request : this.requestAttendanceList) {
            requestListClone.add(request);
        }

        return new Route(nodesVisitationList, vehicleOccupationWhenLeavesNode,
                timeListTheVehicleLeavesTheNode, requestListClone, tempoExtra,
                occupationRate);
    }

}
