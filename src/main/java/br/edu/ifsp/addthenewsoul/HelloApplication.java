package br.edu.ifsp.addthenewsoul;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    public void HidekiTest() throws Exception {
        /*Employee employee = new Employee("asd", "asd", "asd", "asd", "asd", Role.EXECUTOR);
        Location location = new Location(1, 1014, "asd");
        Asset asset = new Asset(1, "asd", employee, 123, "asd", location);

        Map<Integer, Location> locationsMap = new HashMap<>();
        Map<String, Employee> employeesMap = new HashMap<>();

        List<Asset> data = Arrays.asList(asset, asset);

        AssetCSV assetCSV = new AssetCSV();
        assetCSV.write("assets.csv", data);

        List<Asset> assetsFromCSV = assetCSV.read("assets.csv");
        System.out.println(assetsFromCSV.get(0).getDamage());

        locationsMap.put(1, location);
        employeesMap.put(employee.getRegistrationNumber(), employee);

        List<Asset> assetsFromCSVWithDependencies = assetCSV.readWithDependencies(false, "assets.csv", employeesMap, locationsMap);

        System.out.println(assetsFromCSVWithDependencies);

        LocationCSV locationCSV = new LocationCSV();
        locationCSV.write("locations.csv", List.of(location));

        List<Location> locationsFromCSV = locationCSV.read("locations.csv");

        System.out.println(locationsFromCSV);

        EmployeeCSV employeeCSV = new EmployeeCSV();

        employeeCSV.write("employees.csv", List.of(employee));

        List<Employee> employeesFromCSV = employeeCSV.read("employees.csv");

        System.out.println(employeesFromCSV);*/

        //AssetCSVBean csvBean = (AssetCSVBean) csv.get(0);
        //System.out.println(csvBean.toString());

//        Database.getConnection();
//        Statement statement = Database.getStatement();
//
//        try {
//            ResultSet rs = statement.executeQuery("SELECT DATE()");
//            while(rs.next()) {
//                System.out.println(rs.getString(1));
//            }
//
//        } catch (Exception ex) {
//            System.err.println(ex.getMessage());
//        }
//
//        PDDocument document = new PDDocument();
//        PDPage page = new PDPage();
//
//        int pageHeight = (int) page.getTrimBox().getHeight();
//        int pageWidth = (int) page.getTrimBox().getWidth();
//        PDPageContentStream contentStream = new PDPageContentStream(document, page);
//        contentStream.setStrokingColor(0f, 0f, 0f);
//
//        for(int i = 1; i < 10; i++) {
//            for(int j = 1; j < 10; j++) {
//                contentStream.addRect(50 * i, pageHeight - 50 * j, 50, 50);
//            }
//        }
//
//        contentStream.stroke();
//        contentStream.close();
//
//        document.addPage(page);
//
//        document.save("pdf.pdf");
//        document.close();
//
//        String password = "password";
//        String hash = BCrypt.withDefaults().hashToString(12, password.toCharArray());
//        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hash);
//
//        System.out.println(hash);
//        System.out.println(result.verified);
    }

    /*private static void ViniciusTest() {
        List<Asset> assets = new ArrayList<>();

        Employee employee = new Employee("asd", "asd", "asd", "asd", "asd", Role.EXECUTOR);
        Location location = new Location(1, 1014, "asd");
        Asset asset = new Asset(1, "asd", employee, 123, "asd", location);
        Employee employee_2 = new Employee("asd", "ssd", "asd", "asd", "asd", Role.EXECUTOR);
        Location location_2 = new Location(2, 1014, "ssd");
        Asset asset_2 = new Asset(2, "xxv", employee_2, 123, "asd", location_2);
        Employee employee_3 = new Employee("asd", "ssd", "asd", "asd", "asd", Role.INVENTORY_MANAGER);
        Location location_3 = new Location(3, 1014, "ssd");
        Asset asset_3 = new Asset(3, "xxv", employee_3, 453, "asd", location_3);

        InMemoryAssetDAO inMemoryAssetDAO = new InMemoryAssetDAO();

        inMemoryAssetDAO.add(asset);
        inMemoryAssetDAO.add(asset_2);

        System.out.println(inMemoryAssetDAO.findAll());
        /*inMemoryAssetDAO.findById(asset_2.getId());

        inMemoryAssetDAO.add(asset_3);

        assets.add(asset);
        assets.add(asset_2);
        assets.add(asset_3);

        inMemoryAssetDAO.findByEmployee(assets, employee);
        inMemoryAssetDAO.findByLocation(assets, location);
        inMemoryAssetDAO.findByEmployee(assets, employee_3);
        inMemoryAssetDAO.findByLocationAndEmployee(assets, location_2, employee_3);

        asset_2.setValue(987);
        inMemoryAssetDAO.update(asset_2);

        inMemoryAssetDAO.findById(asset_2.getId());

        inMemoryAssetDAO.delete(asset_3.getId());
        inMemoryAssetDAO.findAll();*/



    /*public static void IsaTest() {

        List<Employee> employees = new ArrayList<>();

        Employee employee = new Employee("Fulano", "R123", "abc", "fulano@gmail.com", "11111", Role.EXECUTOR);
        Employee employee1 = new Employee("Ciclano", "R456", "def", "ciclano@gmail.com", "22222", Role.EXECUTOR);
        Employee employee2 = new Employee("Beltrano", "R789", "ghi", "beltrano@gmail.com", "33333", Role.INVENTORY_MANAGER);

        InMemoryEmployeeDAO inMemoryEmployeeDAO = new InMemoryEmployeeDAO();

        inMemoryEmployeeDAO.add(employee);
        inMemoryEmployeeDAO.add(employee1);

        System.out.println(inMemoryEmployeeDAO.findAll());
        System.out.println(inMemoryEmployeeDAO.findByRegistrationNumber(employee1.getRegistrationNumber()));

        inMemoryEmployeeDAO.add(employee2);

        employees.add(employee);
        employees.add(employee1);
        employees.add(employee2);

        employee1.setPhone("99999");
        inMemoryEmployeeDAO.update(employee1);
        System.out.println(employee1);

        System.out.println(inMemoryEmployeeDAO.findByRegistrationNumber(employee1.getRegistrationNumber()));

        inMemoryEmployeeDAO.delete(employee1.getRegistrationNumber());
        System.out.println(inMemoryEmployeeDAO.findAll());
    }*/

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        //ViniciusTest();
        //IsaTest();
        launch();
    }

}