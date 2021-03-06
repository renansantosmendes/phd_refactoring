package ProblemRepresentation;

import GoogleMapsApi.GoogleStaticMap;
import InstanceReader.Instance;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.stream.Collectors;

public class ProblemSolution implements Comparable<ProblemSolution> {

    private List<Double> originalObjectives;
    private List<Double> originalObjectivesNormalized;
    private Set<Route> routes;
    private double objectiveFunction;
    private double totalDistance;//f1
    private double totalDeliveryDelay;//f2
    private double numberOfNonAttendedRequests;//f4
    private double numberOfVehicles;//f5
    private double totalTravelTime;//f6
    private double totalWaintingTime;//f7
    private double deliveryTimeWindowAntecipation;//f8
    private double totalRouteTimeChargeBanlance;//f3
    private double totalOccupationRate;//f9

    private double totalDistanceNormalized;//f1
    private double totalDeliveryDelayNormalized;//f2
    private double numberOfNonAttendedRequestsNormalized;//f4
    private double numberOfVehiclesNormalized;//f5
    private double totalTravelTimeNormalized;//f6
    private double totalWaintingTimeNormalized;//f7
    private double deliveryTimeWindowAntecipationNormalized;//f8
    private double totalRouteTimeChargeBanlanceNormalized;//f3
    private double totalOccupationRateNormalized;//f9

    private double aggregatedObjective1;
    private double aggregatedObjective2;
    private double[] reducedObjectives;
    private int numberOfAggregatedObjectives;
    private double aggregatedObjective1Normalized;
    private double aggregatedObjective2Normalized;

    private int numberOfDominatedSolutionsByThisSolution;
    private int numberOfSolutionsWichDomineThisSolution;
    private List<Integer> listOfSolutionsDominatedByThisSolution;
    private double fitness;
    private int dif;
    private double crowdDistance;
    private int S;
    private int R;
    private List<Request> nonAttendedRequestsList;
    private List<Integer> linkedRouteList;
    private String logger;
    private int extraTimeSum;

    public ProblemSolution() {
        routes = new HashSet<Route>();
        listOfSolutionsDominatedByThisSolution = new ArrayList<>();
        objectiveFunction = -1;
        totalDistance = -1;
        totalDeliveryDelay = -1;
        numberOfNonAttendedRequests = -1;
        numberOfVehicles = -1;
        totalTravelTime = -1;
        totalWaintingTime = -1;
        deliveryTimeWindowAntecipation = -1;
        totalRouteTimeChargeBanlance = -1;
        totalOccupationRate = -1;
        originalObjectives = new ArrayList<>();
        originalObjectivesNormalized = new ArrayList<>();

        totalDistanceNormalized = -1;
        totalDeliveryDelayNormalized = -1;
        numberOfNonAttendedRequestsNormalized = -1;
        numberOfVehiclesNormalized = -1;
        totalTravelTimeNormalized = -1;
        totalWaintingTimeNormalized = -1;
        deliveryTimeWindowAntecipationNormalized = -1;
        totalRouteTimeChargeBanlanceNormalized = -1;
        totalOccupationRateNormalized = -1;

        aggregatedObjective1 = -1;
        aggregatedObjective2 = -1;
        reducedObjectives = new double[11];
        aggregatedObjective1Normalized = 0;
        aggregatedObjective2Normalized = 0;

        R = 0;
        S = 0;
        fitness = -0.9;
        dif = -1;
        crowdDistance = 0.0;
        numberOfDominatedSolutionsByThisSolution = 0;
        numberOfSolutionsWichDomineThisSolution = 0;
        nonAttendedRequestsList = new ArrayList<Request>();
        linkedRouteList = new ArrayList<Integer>();
        logger = "";
    }

    public ProblemSolution(int reducedDimension) {
        routes = new HashSet<Route>();
        listOfSolutionsDominatedByThisSolution = new ArrayList<>();
        objectiveFunction = -1;
        totalDistance = -1;
        totalDeliveryDelay = -1;
        numberOfNonAttendedRequests = -1;
        numberOfVehicles = -1;
        totalTravelTime = -1;
        totalWaintingTime = -1;
        deliveryTimeWindowAntecipation = -1;
        totalRouteTimeChargeBanlance = -1;
        totalOccupationRate = -1;
        originalObjectives = new ArrayList<>();
        originalObjectivesNormalized = new ArrayList<>();

        totalDistanceNormalized = -1;
        totalDeliveryDelayNormalized = -1;
        numberOfNonAttendedRequestsNormalized = -1;
        numberOfVehiclesNormalized = -1;
        totalTravelTimeNormalized = -1;
        totalWaintingTimeNormalized = -1;
        deliveryTimeWindowAntecipationNormalized = -1;
        totalRouteTimeChargeBanlanceNormalized = -1;
        totalOccupationRateNormalized = -1;

        aggregatedObjective1 = -1;
        aggregatedObjective2 = -1;
        reducedObjectives = new double[reducedDimension];
        aggregatedObjective1Normalized = 0;
        aggregatedObjective2Normalized = 0;

        R = 0;
        S = 0;
        fitness = -0.9;
        dif = -1;
        crowdDistance = 0.0;
        numberOfDominatedSolutionsByThisSolution = 0;
        numberOfSolutionsWichDomineThisSolution = 0;
        nonAttendedRequestsList = new ArrayList<Request>();
        linkedRouteList = new ArrayList<Integer>();
        logger = "";
    }

    public ProblemSolution(int numberOfAggregatedObjectives, List<Double> objectives, Set<Route> setOfRoutes, double objectiveFunction, double totalDistance,
            double totalDeliveryDelay, double numberOfNonAttendedRequests, double numberOfVehicles, double totalTravelTime,
            double totalWaintingTime, double deliveryTimeWindowAntecipation, double totalRouteTimeChargeBanlance,
            double totalOccupationRate, double totalDistanceNormalized, double totalDeliveryDelayNormalized,
            double numberOfNonAttendedRequestsNormalized, double numberOfVehiclesNormalized, double totalTravelTimeNormalized,
            double totalWaintingTimeNormalized, double deliveryTimeWindowAntecipationNormalized,
            double totalRouteTimeChargeBanlanceNormalized, double totalOccupationRateNormalized, double aggregatedObjective1,
            double aggregatedObjective2, double aggregatedObjective1Normalized, double aggregatedObjective2Normalized,
            int numberOfDominatedSolutionsByThisSolution, int numberOfSolutionsWichDomineThisSolution,
            List<Integer> listOfSolutionsDominatedByThisSolution, double fitness, int dif, double crowdDistance, int S,
            int R, List<Request> nonAttendedRequestsList, List<Integer> linkedRouteList, String logger, int tempoExtraTotal) {

        this();
        this.originalObjectives.clear();
        this.originalObjectives.addAll(objectives);
        this.routes.clear();
        this.routes.addAll(setOfRoutes);
        this.objectiveFunction = objectiveFunction;
        this.totalDistance = totalDistance;
        this.totalDeliveryDelay = totalDeliveryDelay;
        this.numberOfNonAttendedRequests = numberOfNonAttendedRequests;
        this.numberOfVehicles = numberOfVehicles;
        this.totalTravelTime = totalTravelTime;
        this.totalWaintingTime = totalWaintingTime;
        this.deliveryTimeWindowAntecipation = deliveryTimeWindowAntecipation;
        this.totalRouteTimeChargeBanlance = totalRouteTimeChargeBanlance;
        this.totalOccupationRate = totalOccupationRate;
        this.totalDistanceNormalized = totalDistanceNormalized;
        this.totalDeliveryDelayNormalized = totalDeliveryDelayNormalized;
        this.numberOfNonAttendedRequestsNormalized = numberOfNonAttendedRequestsNormalized;
        this.numberOfVehiclesNormalized = numberOfVehiclesNormalized;
        this.totalTravelTimeNormalized = totalTravelTimeNormalized;
        this.totalWaintingTimeNormalized = totalWaintingTimeNormalized;
        this.deliveryTimeWindowAntecipationNormalized = deliveryTimeWindowAntecipationNormalized;
        this.totalRouteTimeChargeBanlanceNormalized = totalRouteTimeChargeBanlanceNormalized;
        this.totalOccupationRateNormalized = totalOccupationRateNormalized;

        this.numberOfAggregatedObjectives = numberOfAggregatedObjectives;
        this.reducedObjectives = new double[this.numberOfAggregatedObjectives];
        this.aggregatedObjective1 = aggregatedObjective1;
        this.aggregatedObjective2 = aggregatedObjective2;
        this.aggregatedObjective1Normalized = aggregatedObjective1Normalized;
        this.aggregatedObjective2Normalized = aggregatedObjective2Normalized;
        this.numberOfDominatedSolutionsByThisSolution = numberOfDominatedSolutionsByThisSolution;
        this.numberOfSolutionsWichDomineThisSolution = numberOfSolutionsWichDomineThisSolution;

        this.listOfSolutionsDominatedByThisSolution.clear();
        this.listOfSolutionsDominatedByThisSolution.addAll(listOfSolutionsDominatedByThisSolution);
        this.fitness = fitness;
        this.dif = dif;
        this.crowdDistance = crowdDistance;
        this.S = S;
        this.R = R;

        this.nonAttendedRequestsList.clear();
        this.nonAttendedRequestsList.addAll(nonAttendedRequestsList);

        this.linkedRouteList.clear();
        this.linkedRouteList.addAll(linkedRouteList);
        this.logger = logger;
        this.extraTimeSum = tempoExtraTotal;
    }

    public ProblemSolution(ProblemSolution solution) {
        routes = new HashSet<Route>(solution.getSetOfRoutes());
        listOfSolutionsDominatedByThisSolution = new ArrayList<>(solution.getListOfSolutionsDominatedByThisSolution());
        objectiveFunction = solution.getObjectiveFunction();
        totalDistance = solution.getTotalDistance();
        totalDeliveryDelay = solution.getTotalDeliveryDelay();
        numberOfNonAttendedRequests = solution.getNumberOfNonAttendedRequests();
        numberOfVehicles = solution.getNumberOfVehicles();
        totalTravelTime =  solution.getTotalTravelTime();
        totalWaintingTime =  solution.getTotalWaintingTime();
        deliveryTimeWindowAntecipation =  solution.getDeliveryTimeWindowAntecipation();
        totalRouteTimeChargeBanlance =  solution.getTotalRouteTimeChargeBanlance();
        totalOccupationRate = solution.getTotalOccupationRate();
        originalObjectives.addAll(solution.getOriginalObjectives());
        originalObjectivesNormalized.addAll(solution.getOriginalObjectivesNormalized());

        totalDistanceNormalized = solution.getTotalDistanceNormalized();
        totalDeliveryDelayNormalized = solution.getTotalDeliveryDelayNormalized();
        numberOfNonAttendedRequestsNormalized = solution.getNumberOfNonAttendedRequestsNormalized();
        numberOfVehiclesNormalized = solution.getNumberOfVehiclesNormalized();
        totalTravelTimeNormalized = solution.getTotalTravelTimeNormalized();
        totalWaintingTimeNormalized = solution.getTotalWaintingTimeNormalized();
        deliveryTimeWindowAntecipationNormalized = solution.getDeliveryTimeWindowAntecipationNormalized();
        totalRouteTimeChargeBanlanceNormalized = solution.getTotalRouteTimeChargeBanlanceNormalized();
        totalOccupationRateNormalized = solution.getTotalOccupationRateNormalized();

        reducedObjectives = solution.getAggregatedObjectives();
        numberOfAggregatedObjectives = solution.getNumberOfAggregatedObjectives();

        aggregatedObjective1 = solution.getAggregatedObjective1();
        aggregatedObjective2 = solution.getAggregatedObjective2();
        aggregatedObjective1Normalized = solution.getAggregatedObjective1Normalized();
        aggregatedObjective2Normalized = solution.getAggregatedObjective2Normalized();
        fitness = solution.getFitness();

        extraTimeSum = solution.getTempoExtraTotal();
        nonAttendedRequestsList = new ArrayList<Request>(solution.getNonAttendedRequestsList());
        linkedRouteList = new ArrayList<Integer>(solution.getLinkedRouteList());
        logger = new String(solution.getLogger());
    }

    public ProblemSolution(List<Integer> concatenatedRoutes) {
        this.linkedRouteList.addAll(linkedRouteList);

    }

    public void setSolution(ProblemSolution solution) {
        setRoutes(solution.getSetOfRoutes());
        setListOfSolutionsDominatedByThisSolution(solution.getListOfSolutionsDominatedByThisSolution());
        setObjectiveFunction(solution.getObjectiveFunction());

        setTotalDistance(solution.getTotalDistance());
        setTotalDeliveryDelay(solution.getTotalDeliveryDelay());
        setTotalRouteTimeChargeBanlance(solution.getTotalRouteTimeChargeBanlance());
        setNumberOfNonAttendedRequests(solution.getNumberOfNonAttendedRequests());
        setNumberOfVehicles(solution.getNumberOfVehicles());
        setTotalTravelTime(solution.getTotalTravelTime());
        setTotalWaintingTime(solution.getTotalWaintingTime());
        setDeliveryTimeWindowAntecipation(solution.getDeliveryTimeWindowAntecipation());
        setTotalOccupationRate(solution.getTotalOccupationRate());
        setOriginalObjectives(solution.getOriginalObjectives());
        setOriginalObjectivesNormalized(solution.getOriginalObjectivesNormalized());

        setTotalDistanceNormalized(solution.getTotalDistanceNormalized());
        setTotalDeliveryDelayNormalized(solution.getTotalDeliveryDelayNormalized());
        setTotalRouteTimeChargeBanlanceNormalized(solution.getTotalRouteTimeChargeBanlanceNormalized());
        setNumberOfNonAttendedRequestsNormalized(solution.getNumberOfNonAttendedRequestsNormalized());
        setNumberOfVehiclesNormalized(solution.getNumberOfVehiclesNormalized());
        setTotalTravelTimeNormalized(solution.getTotalTravelTimeNormalized());
        setTotalWaintingTimeNormalized(solution.getTotalWaintingTimeNormalized());
        setDeliveryTimeWindowAntecipationNormalized(solution.getDeliveryTimeWindowAntecipationNormalized());
        setTotalOccupationRateNormalized(solution.getTotalOccupationRateNormalized());

        setAggregatedObjectives(solution.getAggregatedObjectives().clone());
        setNumberOfAggregatedObjectives(solution.getNumberOfAggregatedObjectives());

        setAggregatedObjective1(solution.getAggregatedObjective1());
        setAggregatedObjective2(solution.getAggregatedObjective2());
        setAggregatedObjective1Normalized(solution.getAggregatedObjective1Normalized());
        setAggregatedObjective2Normalized(solution.getAggregatedObjective2Normalized());
        setFitness(solution.getFitness());

        setR(solution.getR());
        setS(solution.getS());
        setNonAttendedRequestsList(solution.getNonAttendedRequestsList());
        setLinkedRouteList(solution.getLinkedRouteList());
        setLogger(solution.getLogger());
        setExtraTimeSum(solution.getTempoExtraTotal());
    }

    public void resetSolution(double FO, int FO1, int FO2, long FO3, int FO4, int FO5, int FO6,
            int FO7, int FO8, double F9) {
        routes.clear();
        listOfSolutionsDominatedByThisSolution.clear();
        objectiveFunction = FO;

        totalDistance = FO1;
        totalDeliveryDelay = FO2;
        totalRouteTimeChargeBanlance = FO3;
        numberOfNonAttendedRequests = FO4;
        numberOfVehicles = FO5;
        totalTravelTime = FO6;
        totalWaintingTime = FO7;
        deliveryTimeWindowAntecipation = FO8;
        totalOccupationRate = F9;

        aggregatedObjective1 = 99999999;
        aggregatedObjective2 = 99999999;
        aggregatedObjective1Normalized = 99999999;
        aggregatedObjective2Normalized = 99999999;
        nonAttendedRequestsList.clear();
        linkedRouteList.clear();
        logger = "";
    }

    public void setObjectivesList() {
        this.originalObjectives.clear();
        this.originalObjectives.add((double) this.totalDistance);//f1
        this.originalObjectives.add((double) this.totalDeliveryDelay);//f2
        this.originalObjectives.add((double) this.totalRouteTimeChargeBanlance);//f3
        //this.originalObjectives.add((double) this.numberOfNonAttendedRequests);//f4
        this.originalObjectives.add((double) this.numberOfVehicles);//f5
        this.originalObjectives.add((double) this.totalTravelTime);//f6
        this.originalObjectives.add((double) this.totalWaintingTime);//f7
        this.originalObjectives.add((double) this.deliveryTimeWindowAntecipation);//f8
        this.originalObjectives.add((double) this.totalOccupationRate);//f9

    }
    
    public void setObjectivesNormalizedList() {
        this.originalObjectivesNormalized.clear();
        this.originalObjectivesNormalized.add((double) this.totalDistanceNormalized);//f1
        this.originalObjectivesNormalized.add((double) this.totalDeliveryDelayNormalized);//f2
        this.originalObjectivesNormalized.add((double) this.totalRouteTimeChargeBanlanceNormalized);//f3
        this.originalObjectivesNormalized.add((double) this.numberOfVehiclesNormalized);//f5
        this.originalObjectivesNormalized.add((double) this.totalTravelTimeNormalized);//f6
        this.originalObjectivesNormalized.add((double) this.totalWaintingTimeNormalized);//f7
        this.originalObjectivesNormalized.add((double) this.deliveryTimeWindowAntecipationNormalized);//f8
        this.originalObjectivesNormalized.add((double) this.totalOccupationRateNormalized);//f9

    }

    
    public void setObjectivesList(Parameters parameters) {
        this.originalObjectives.clear();
        this.originalObjectives.add((double) this.totalDistance);//f1
        this.originalObjectives.add((double) this.totalDeliveryDelay);//f2
        this.originalObjectives.add((double) this.totalRouteTimeChargeBanlance);//f3
        //this.originalObjectives.add((double) this.numberOfNonAttendedRequests);//f4
        this.originalObjectives.add((double) this.numberOfVehicles);//f5
        this.originalObjectives.add((double) this.totalTravelTime);//f6
        this.originalObjectives.add((double) this.totalWaintingTime);//f7
        this.originalObjectives.add((double) this.deliveryTimeWindowAntecipation);//f8
        this.originalObjectives.add((double) this.totalOccupationRate);//f9

    }

    public int getAttendanceRequestSize() {
        int numberOfAttendedRequests = 0;
        for (Route route : this.routes) {
            numberOfAttendedRequests += route.getRequestAttendanceList().size();
        }

        return numberOfAttendedRequests;
    }

    public List<Integer> getAttendanceRequestList() {
        List<Integer> attendedRequests = new ArrayList<>();
        for (Route route : this.routes) {
            attendedRequests.addAll(route.getRequestAttendanceIdsList());
        }
        return attendedRequests;
    }

    public double getObjective(int objectiveNumber) {
        return this.reducedObjectives[objectiveNumber];
    }

    public double[] getAggregatedObjectives() {
        return this.reducedObjectives.clone();
    }

    public int getNumberOfAggregatedObjectives() {
        return this.numberOfAggregatedObjectives;
    }

    public void setNumberOfAggregatedObjectives(int reducedDimension) {
        this.numberOfAggregatedObjectives = reducedDimension;
    }

    public List<Double> getListOfAggregatedObjectives() {
        List<Double> aggregatedObjectives = new ArrayList<>();

        for (int objectiveNumber = 0; objectiveNumber < this.reducedObjectives.length; objectiveNumber++) {
            aggregatedObjectives.add(this.reducedObjectives[objectiveNumber]);
        }
        return aggregatedObjectives;
    }

    public Set<Route> getSetOfRoutes() {
        return routes;
    }

    public Set<List<Integer>> getRoutesForMapCreation() {
        Set<List<Integer>> routes = new HashSet<>();
        for (Route route : this.getSetOfRoutes()) {
            routes.add(route.getNodesVisitationList());
        }
        return routes;
    }

    public List<List<Integer>> getRoutesListForMapCreation() {
        List<List<Integer>> routes = new ArrayList<>();
        for (Route route : this.getSetOfRoutes()) {
            routes.add(new ArrayList<>(route.getNodesVisitationList()));
        }
        return routes;
    }

    public void getStaticMapWithAllRoutes(List<Node> nodesList, String adjacenciesTable, String nodesTable) throws IOException {
        new GoogleStaticMap(nodesList, this.getRoutesForMapCreation(), adjacenciesTable, nodesTable);
    }

    public void getStaticMapForEveryRoute(List<Node> nodesList, String adjacenciesTable, String nodesTable) throws IOException {
        for (List<Integer> route : this.getRoutesListForMapCreation()) {
            new GoogleStaticMap(nodesList, route, adjacenciesTable, nodesTable);
        }
    }

    public List<Integer> getListOfSolutionsDominatedByThisSolution() {
        return this.listOfSolutionsDominatedByThisSolution;
    }

    public int getR() {
        return R;
    }

    public int getS() {
        return S;
    }

    public void setR(int R) {
        this.R = R;
    }

    public void setS(int S) {
        this.S = S;
    }

    public void setCrowdDistance(double crowdDistance) {
        this.crowdDistance = crowdDistance;
    }

    public void setAggregatedObjectives(double[] objectives) {
        this.reducedObjectives = objectives;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes.clear();
        this.routes.addAll(new HashSet<Route>(routes));
    }

    public void setListOfSolutionsDominatedByThisSolution(List<Integer> listOfSolutionsDominatedByThisSolution) {
        this.listOfSolutionsDominatedByThisSolution.clear();
        this.listOfSolutionsDominatedByThisSolution.addAll(listOfSolutionsDominatedByThisSolution);
    }

    public void addL(int position) {
        this.listOfSolutionsDominatedByThisSolution.add(position);
    }

    public void addnDom() {
        this.numberOfDominatedSolutionsByThisSolution++;
    }

    public void addeDom() {
        this.numberOfSolutionsWichDomineThisSolution++;
    }

    public void setNumberOfDominatedSolutionsByThisSolution(int numberOfDominatedSolutionsByThisSolution) {
        this.numberOfDominatedSolutionsByThisSolution = numberOfDominatedSolutionsByThisSolution;
    }

    public void setNumberOfSolutionsWichDomineThisSolution(int numberOfSolutionsWichDomineThisSolution) {
        this.numberOfSolutionsWichDomineThisSolution = numberOfSolutionsWichDomineThisSolution;
    }

    public void redeDom() {
        this.numberOfSolutionsWichDomineThisSolution--;
    }

    public double getObjectiveFunction() {
        return this.objectiveFunction;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public double getTotalDeliveryDelay() {
        return totalDeliveryDelay;
    }

    public double getNumberOfNonAttendedRequests() {
        return numberOfNonAttendedRequests;
    }

    public double getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public double getTotalTravelTime() {
        return totalTravelTime;
    }

    public double getTotalWaintingTime() {
        return totalWaintingTime;
    }

    public double getDeliveryTimeWindowAntecipation() {
        return deliveryTimeWindowAntecipation;
    }

    public double getTotalRouteTimeChargeBanlance() {
        return totalRouteTimeChargeBanlance;
    }

    public double getTotalOccupationRate() {
        return totalOccupationRate;
    }

    public double getAggregatedObjective1() {
        return this.aggregatedObjective1;
    }

    public double getAggregatedObjective2() {
        return this.aggregatedObjective2;
    }

    public double getAggregatedObjective1Normalized() {
        return this.aggregatedObjective1Normalized;
    }

    public double getAggregatedObjective2Normalized() {
        return this.aggregatedObjective2Normalized;
    }

    public double getFitness() {
        return this.fitness;
    }

    public int getNumberOfDominatedSolutionsByThisSolution() {
        return this.numberOfDominatedSolutionsByThisSolution;
    }

    public int getNumberOfSolutionsWichDomineThisSolution() {
        return this.numberOfSolutionsWichDomineThisSolution;
    }

    public int getTempoExtraTotal() {
        return this.extraTimeSum;
    }

    public double getTotalDistanceNormalized() {
        return totalDistanceNormalized;
    }

    public double getTotalDeliveryDelayNormalized() {
        return totalDeliveryDelayNormalized;
    }

    public double getNumberOfNonAttendedRequestsNormalized() {
        return numberOfNonAttendedRequestsNormalized;
    }

    public double getNumberOfVehiclesNormalized() {
        return numberOfVehiclesNormalized;
    }

    public double getTotalTravelTimeNormalized() {
        return totalTravelTimeNormalized;
    }

    public double getTotalWaintingTimeNormalized() {
        return totalWaintingTimeNormalized;
    }

    public double getDeliveryTimeWindowAntecipationNormalized() {
        return deliveryTimeWindowAntecipationNormalized;
    }

    public double getTotalRouteTimeChargeBanlanceNormalized() {
        return totalRouteTimeChargeBanlanceNormalized;
    }

    public double getTotalOccupationRateNormalized() {
        return totalOccupationRateNormalized;
    }

    public double getCrowdDistance() {
        return crowdDistance;
    }

    public List<Double> getOriginalObjectives() {
        return originalObjectives;
    }
    
    public List<Double> getOriginalObjectivesNormalized() {
        return originalObjectivesNormalized;
    }
    
    public void setExtraTimeSum(int tempo) {
        this.extraTimeSum = tempo;
    }

    public void setObjectiveFunction(double objectiveFunction) {
        this.objectiveFunction = objectiveFunction;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public void setTotalDeliveryDelay(double totalDeliveryDelay) {
        this.totalDeliveryDelay = totalDeliveryDelay;
    }

    public void setNumberOfNonAttendedRequests(double numberOfNonAttendedRequests) {
        this.numberOfNonAttendedRequests = numberOfNonAttendedRequests;
    }

    public void setNumberOfVehicles(double numberOfVehicles) {
        this.numberOfVehicles = numberOfVehicles;
    }

    public void setTotalTravelTime(double totalTravelTime) {
        this.totalTravelTime = totalTravelTime;
    }

    public void setTotalWaintingTime(double totalWaintingTime) {
        this.totalWaintingTime = totalWaintingTime;
    }

    public void setDeliveryTimeWindowAntecipation(double deliveryTimeWindowAntecipation) {
        this.deliveryTimeWindowAntecipation = deliveryTimeWindowAntecipation;
    }

    public void setTotalRouteTimeChargeBanlance(double totalRouteTimeChargeBanlance) {
        this.totalRouteTimeChargeBanlance = totalRouteTimeChargeBanlance;
    }

    public void setTotalOccupationRate(double totalOccupationRate) {
        this.totalOccupationRate = totalOccupationRate;
    }

    public void setAggregatedObjective1(double aggregatedObjective1) {
        this.aggregatedObjective1 = aggregatedObjective1;
    }

    public void setAggregatedObjective2(double aggregatedObjective2) {
        this.aggregatedObjective2 = aggregatedObjective2;
    }

    public void setAggregatedObjective1Normalized(double aggregatedObjective1Normalized) {
        this.aggregatedObjective1Normalized = aggregatedObjective1Normalized;
    }

    public void setAggregatedObjective2Normalized(double aggregatedObjective2Normalized) {
        this.aggregatedObjective2Normalized = aggregatedObjective2Normalized;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public List<Request> getNonAttendedRequestsList() {
        return nonAttendedRequestsList;
    }

    public void setNonAttendedRequestsList(List<Request> listaNaoAtendimento) {
        this.nonAttendedRequestsList.clear();
        this.nonAttendedRequestsList.addAll(new LinkedList<Request>(listaNaoAtendimento));
    }

    public void setOriginalObjectives(List<Double> originalObjectives) {
        this.originalObjectives = originalObjectives;
    }

    public void setOriginalObjectivesNormalized(List<Double> originalObjectivesNormalized) {
        this.originalObjectivesNormalized = originalObjectivesNormalized;
    }
    
    public List<Integer> getLinkedRouteList() {
        return linkedRouteList;
    }

    public void setLinkedRouteList(List<Integer> linkedRouteList) {
        this.linkedRouteList.clear();
        this.linkedRouteList.addAll(new ArrayList<Integer>(linkedRouteList));
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;

    }

    public void setDif(int dif) {
        this.dif = dif;
    }

    public void setTotalDistanceNormalized(double totalDistanceNormalized) {
        this.totalDistanceNormalized = totalDistanceNormalized;
    }

    public void setTotalDeliveryDelayNormalized(double totalDeliveryDelayNormalized) {
        this.totalDeliveryDelayNormalized = totalDeliveryDelayNormalized;
    }

    public void setNumberOfNonAttendedRequestsNormalized(double numberOfNonAttendedRequestsNormalized) {
        this.numberOfNonAttendedRequestsNormalized = numberOfNonAttendedRequestsNormalized;
    }

    public void setNumberOfVehiclesNormalized(double numberOfVehiclesNormalized) {
        this.numberOfVehiclesNormalized = numberOfVehiclesNormalized;
    }

    public void setTotalTravelTimeNormalized(double totalTravelTimeNormalized) {
        this.totalTravelTimeNormalized = totalTravelTimeNormalized;
    }

    public void setTotalWaintingTimeNormalized(double totalWaintingTimeNormalized) {
        this.totalWaintingTimeNormalized = totalWaintingTimeNormalized;
    }

    public void setDeliveryTimeWindowAntecipationNormalized(double deliveryTimeWindowAntecipationNormalized) {
        this.deliveryTimeWindowAntecipationNormalized = deliveryTimeWindowAntecipationNormalized;
    }

    public void setTotalRouteTimeChargeBanlanceNormalized(double totalRouteTimeChargeBanlanceNormalized) {
        this.totalRouteTimeChargeBanlanceNormalized = totalRouteTimeChargeBanlanceNormalized;
    }

    public void setTotalOccupationRateNormalized(double totalOccupationRateNormalized) {
        this.totalOccupationRateNormalized = totalOccupationRateNormalized;
    }

    public void linkRoutes() {
        for (Route r : routes) {
            linkedRouteList.addAll(r.getNodesVisitationList().subList(1, r.getNodesVisitationList().size() - 1));
        }
    }

    public void addNonAttendeRequest(Request request) {
        nonAttendedRequestsList.add(request);
    }

    public void removeNonAttendeRequest(Request request) {
        nonAttendedRequestsList.remove(request);
    }

    public void normalizeTotalDistance(long minDistance, long maxDistance) {
        double normalizedValue = (double) (this.getTotalDistance() - minDistance) / (maxDistance - minDistance);
        this.setTotalDistanceNormalized(normalizedValue);
    }

    public void normalizeTotalDeliveryDelay(long minDeliveryDelay, long maxDeliveryDelay) {
        this.setTotalDeliveryDelayNormalized((double) (this.getTotalDeliveryDelay() - minDeliveryDelay)
                / (maxDeliveryDelay - minDeliveryDelay));
    }

    public void normalizeTotalRouteTimeChargeBanlance(long minChargeBalance, long maxChargeBalance) {
        this.setTotalRouteTimeChargeBanlanceNormalized((double) (this.getTotalRouteTimeChargeBanlance() - minChargeBalance)
                / (maxChargeBalance - minChargeBalance));
    }

    public void normalizeNumberOfNonAttendedRequests(long minNumberOfNonAttendedRequests, long maxNumberOfNonAttendedRequests) {
        if (minNumberOfNonAttendedRequests != maxNumberOfNonAttendedRequests) {
            this.setNumberOfNonAttendedRequestsNormalized((double) (this.getNumberOfNonAttendedRequests() - minNumberOfNonAttendedRequests)
                    / (maxNumberOfNonAttendedRequests - minNumberOfNonAttendedRequests));
        } else {
            this.setNumberOfNonAttendedRequestsNormalized(1.0);
        }
    }

    public void normalizeNumberOfVehicles(long minNumberOfVehicles, long maxNumberOfVehicles) {
        this.setNumberOfVehiclesNormalized((double) (this.getNumberOfVehicles() - minNumberOfVehicles)
                / (maxNumberOfVehicles - minNumberOfVehicles));
    }

    public void normalizeTotalTravelTime(long minTravelTime, long maxTravelTime) {
        this.setTotalTravelTimeNormalized((double) (this.getTotalTravelTime() - minTravelTime)
                / (maxTravelTime - minTravelTime));
    }

    public void normalizeTotalWaintingTime(long minWaintingTime, long maxWaitingTime) {
        this.setTotalWaintingTimeNormalized((double) (this.getTotalWaintingTime() - minWaintingTime)
                / (maxWaitingTime - minWaintingTime));
    }

    public void normalizeDeliveryTimeWindowAntecipation(long minAntecipation, long maxAntecipation) {
        this.setDeliveryTimeWindowAntecipationNormalized((double) (this.getDeliveryTimeWindowAntecipation() - minAntecipation)
                / (maxAntecipation - minAntecipation));
    }

    public void normalizeTotalOccupationRate(double minOccupationRate, double maxOccupationRate) {
        this.setTotalOccupationRateNormalized((double) (maxOccupationRate - this.getTotalOccupationRate())
                / (maxOccupationRate - minOccupationRate));
    }

    public String getStringWithOriginalObjectives() {
        String stringWithObjectives = totalDistance + "\t"
                + totalDeliveryDelay + "\t" + totalRouteTimeChargeBanlance + "\t" + numberOfNonAttendedRequests + "\t"
                + numberOfVehicles + "\t" + totalTravelTime + "\t" + totalWaintingTime + "\t"
                + deliveryTimeWindowAntecipation + "\t" + totalOccupationRate + "\t";
        return stringWithObjectives;
    }

    public String getStringWithReducedObjectives() {
        String string = "";
        for (int i = 0; i < this.reducedObjectives.length; i++) {
            if (i != this.reducedObjectives.length - 1) {
                string += this.reducedObjectives[i] + ",";
            } else {
                string += this.reducedObjectives[i];
            }
        }
        return string;
    }

    public String getStringWithOriginalObjectivesForCsvFile() {
        String stringWithObjectives = totalDistance + ","
                + totalDeliveryDelay + "," + 6.9/*totalRouteTimeChargeBanlance*/ + ","
                + numberOfVehicles + "," + totalTravelTime + "," + totalWaintingTime + ","
                + deliveryTimeWindowAntecipation + "," + totalOccupationRate + "\t";
        return stringWithObjectives;
    }
    
    public String getStringWithNormalizedOriginalObjectivesForCsvFile() {
        String stringWithObjectives = totalDistanceNormalized + ","
                + totalDeliveryDelayNormalized + "," + totalRouteTimeChargeBanlanceNormalized + ","
                + numberOfVehiclesNormalized + "," + totalTravelTimeNormalized + "," + totalWaintingTimeNormalized + ","
                + deliveryTimeWindowAntecipationNormalized + "," + totalOccupationRateNormalized + "\t";
        return stringWithObjectives;
    }
    
    public double[] getOriginalObjectivesArray() {
        int size = originalObjectives.size();
        double[] array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = originalObjectives.get(i);
        }
        return array;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.0000");
        String s = this.originalObjectives + "\t" + getListOfAggregatedObjectives() + "\t" + totalDistance + "\t"
                + totalDeliveryDelay + "\t" + totalRouteTimeChargeBanlance + "\t"
                + numberOfVehicles + "\t" + totalWaintingTime + "\t" + totalTravelTime + "\t" + deliveryTimeWindowAntecipation
                + "\t" + totalOccupationRate + "\t";
        
        String sNormalized = totalDistanceNormalized + "\t"
                + totalDeliveryDelayNormalized + "\t" + totalRouteTimeChargeBanlanceNormalized + "\t"
                + numberOfVehiclesNormalized + "\t" + totalWaintingTimeNormalized + "\t" + totalTravelTimeNormalized + 
                "\t" + deliveryTimeWindowAntecipation  + "\t" + totalOccupationRateNormalized+ "\t";

        
        s = getListOfAggregatedObjectives() + "\t" + getStringWithOriginalObjectivesForCsvFile() + " " + 
                getStringWithNormalizedOriginalObjectivesForCsvFile() + " " + this.getAttendanceRequestSize() + " ";
        
        int routeNumber = 1;
        String attendanceList = " ";
        for (Route r : routes) {
            s += "R" + routeNumber + ": " + r + " ";
            attendanceList += "R" + routeNumber++ + ": ";
            for (Request req : r.getRequestAttendanceList()) {
                attendanceList += req + " ";
            }
        }
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ProblemSolution && equals((ProblemSolution) obj);
    }

    public boolean equals(ProblemSolution solution) {
        if (this == solution) {
            return true;
        }

        if (solution == null) {
            return false;
        }

        if (routes.size() != solution.getSetOfRoutes().size()) {
            return false;
        }

        for (Iterator<Route> route = routes.iterator(); route.hasNext();) {
            if (!solution.getSetOfRoutes().contains(route.next())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {

        if (routes == null) {
            return -1;
        }

        int hash = 0;

        for (Route route : routes) {
            hash += route.hashCode();
        }

        return hash;
    }

    @Override
    public int compareTo(ProblemSolution solution) {
        if (this.getFitness() > solution.getFitness()) {
            return 1;
        }
        if (this.getFitness() < solution.getFitness()) {
            return -1;
        }
        return 0;
    }

    @Override
    public Object clone() {

        Set<Route> routesClone = new HashSet<>();
        for (Route route : routes) {
            routesClone.add((Route) route.clone());
        }

        return new ProblemSolution(numberOfAggregatedObjectives, originalObjectives, routesClone, objectiveFunction, totalDistance,
                totalDeliveryDelay, numberOfNonAttendedRequests, numberOfVehicles, totalTravelTime,
                totalWaintingTime, deliveryTimeWindowAntecipation, totalRouteTimeChargeBanlance,
                totalOccupationRate, totalDistanceNormalized, totalDeliveryDelayNormalized,
                numberOfNonAttendedRequestsNormalized, numberOfVehiclesNormalized, totalTravelTimeNormalized,
                totalWaintingTimeNormalized, deliveryTimeWindowAntecipationNormalized,
                totalRouteTimeChargeBanlanceNormalized, totalOccupationRateNormalized, aggregatedObjective1,
                aggregatedObjective2, aggregatedObjective1Normalized, aggregatedObjective2Normalized,
                numberOfDominatedSolutionsByThisSolution, numberOfSolutionsWichDomineThisSolution,
                listOfSolutionsDominatedByThisSolution, fitness, dif, crowdDistance, S,
                R, nonAttendedRequestsList, linkedRouteList, logger, extraTimeSum
        );
    }

    public void evaluate(List<List<Long>> distanceBetweenNodes, Integer vehicleCapacity) {
        this.setTotalDistance(objectiveFunction1(distanceBetweenNodes));
        this.setTotalDeliveryDelay(objectiveFunction2());
        this.setTotalRouteTimeChargeBanlance(objectiveFunction3());
        this.setNumberOfNonAttendedRequests(objectiveFunction4());
        this.setNumberOfVehicles(objectiveFunction5());
        this.setTotalTravelTime(objectiveFunction6());
        this.setTotalWaintingTime(objectiveFunction7());
        this.setDeliveryTimeWindowAntecipation(objectiveFunction8());
        this.setTotalOccupationRate(objectiveFunction9(vehicleCapacity));
        this.setObjectivesList();
        this.setObjectiveFunction(evaluationFunction());
    }

    public void evaluate(List<List<Long>> distanceBetweenNodes, Integer vehicleCapacity, Parameters parameters) {
        this.setTotalDistance(objectiveFunction1(distanceBetweenNodes));
        this.setTotalDeliveryDelay(objectiveFunction2());
        this.setTotalRouteTimeChargeBanlance(objectiveFunction3());
        this.setNumberOfNonAttendedRequests(objectiveFunction4());
        this.setNumberOfVehicles(objectiveFunction5());
        this.setTotalTravelTime(objectiveFunction6());
        this.setTotalWaintingTime(objectiveFunction7());
        this.setDeliveryTimeWindowAntecipation(objectiveFunction8());
        this.setTotalOccupationRate(objectiveFunction9(vehicleCapacity));
        this.setObjectivesList(parameters);
        this.setObjectiveFunction(evaluationFunction());
    }

    public long objectiveFunction1(List<List<Long>> distanceBetweenNodes) {
        int totalCost = 0;
        int W = 1000,//1000,
                costU = 0;//800;//200;
        for (Route route : this.getSetOfRoutes()) {
            totalCost += W;
            for (int i = 0, j = route.getNodesVisitationList().size(); i < j - 1; i++) {
                totalCost += distanceBetweenNodes.get(route.getNodesVisitationList().get(i)).get(route.getNodesVisitationList().get(i + 1));
            }
        }
        totalCost += this.getNonAttendedRequestsList().size() * costU;
        return totalCost;
    }

    public long objectiveFunction2() {
        int totalSum = 0;
        for (Route routes : this.getSetOfRoutes()) {
            int sum = 0;
            for (Request request : routes.getRequestAttendanceList()) {
                if (request.getDeliveryTime() > request.getDeliveryTimeWindowUpper()) {
                    long dif = request.getDeliveryTime() - request.getDeliveryTimeWindowUpper();
                    sum += dif;
                }
            }
            routes.setExtraTime(sum);
            totalSum += sum;
        }
        return totalSum;
    }

    public long objectiveFunction3() {
        Set<Route> routes = new HashSet<>();
        routes.addAll(this.getSetOfRoutes());
        List<Long> routeEndTime = new ArrayList<>();
        for (Route route : routes) {
            long time = route.getTimeListTheVehicleLeavesTheNode().get(route.getNodesVisitationList().size() - 2);
            routeEndTime.add(time);
        }
        routeEndTime.sort(Comparator.naturalOrder());

        Long greaterRoute = routeEndTime.get(routeEndTime.size() - 1);
        Long smallerRoute = routeEndTime.get(0);

        return greaterRoute - smallerRoute;
    }

    public int objectiveFunction4() {
        return this.getNonAttendedRequestsList().size();
    }

    public int objectiveFunction5() {
        return this.getSetOfRoutes().size();
    }

    public int objectiveFunction6() {
        Set<Route> routes = new HashSet<>();
        routes.addAll(this.getSetOfRoutes());
        int sum = 0;
        for (Route route : routes) {
            for (Request request : route.getRequestAttendanceList()) {
                sum += request.getDeliveryTime() - request.getPickupTime();
            }
        }
        return sum;
    }

    public int objectiveFunction7() {
        Set<Route> routes = new HashSet<>();
        routes.addAll(this.getSetOfRoutes());
        int sum = 0;
        for (Route route : routes) {
            for (Request request : route.getRequestAttendanceList()) {
                sum += request.getPickupTime() - request.getPickupTimeWindowLower();
            }
        }
        return sum;
    }

    public int objectiveFunction8() {
        Set<Route> routes = new HashSet<>();
        routes.addAll(this.getSetOfRoutes());
        int sum = 0;
        for (Route route : routes) {
            for (Request request : route.getRequestAttendanceList()) {
                sum += Math.abs(Math.max(request.getDeliveryTimeWindowLower() - request.getDeliveryTime(), 0));
            }
        }
        return sum;
    }

    public double objectiveFunction9(int vehicleCapacity) {
        this.getSetOfRoutes().forEach(route -> route.calculateOccupationRate(vehicleCapacity));
        List<Double> routesOccupationRate = this.getSetOfRoutes().stream().map(Route::getOccupationRate)
                .collect(Collectors.toCollection(ArrayList::new));
        double totalOccupationRate = routesOccupationRate.stream().mapToDouble(Double::valueOf).average().getAsDouble();
        return 1 - totalOccupationRate;
    }

    public double evaluationFunction() {
        double evalutaion = 0;
        double V = 12;
        double beta = 1 / 10;
        double gama = 1;
        int Y = 1000;
        int W = 800;
        evalutaion = this.getTotalDistance() + this.getSetOfRoutes().size() * W + this.getNonAttendedRequestsList().size() * Y;
        return evalutaion;
    }
}
