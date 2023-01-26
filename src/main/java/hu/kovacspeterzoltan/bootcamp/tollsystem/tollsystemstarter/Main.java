package hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystemstarter;

import hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystemconsoleui.controller.TollSystemConsoleUIController;
import hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystemconsoleui.presenter.TollSystemAppPresenterImp;
import hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystemconsoleui.view.TollSystemConsoleUIView;
import hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystempersistencestorage.TollSystemPersistenceCSVStorage;
import hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystempersistencestorage.TollSystemPersistenceSQLiteStorage;
import hu.kovacspeterzoltan.bootcamp.tollsystem.vehicleregisterplugin.VehicleRegisterFindInteractor;
import hu.kovacspeterzoltan.bootcamp.tollsystem.vehicleregisterplugin.presenter.VehicleRegisterPresenterImp;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.MotorwayVignetteInteractor;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.api.MotorwayVignettePresenterInterface;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.storage.MotorwayVignetteStorageInterface;
import hu.kovacspeterzoltan.bootcamp.vehiclepersistencestorage.VehicleRegisterPersistenceStorageCSVPlugIn;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.VehicleInteractor;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.storage.VehicleRegisterStoragePlugInInterface;

public class Main {
    public static void main(String[] args) {
        VehicleInteractor vehicleInteractor = new VehicleInteractor();

        VehicleRegisterStoragePlugInInterface vehicleStorage = new VehicleRegisterPersistenceStorageCSVPlugIn();
        vehicleInteractor.setStoragePlugInImp(vehicleStorage);

        VehicleRegisterPresenterImp vehicleRegisterPresenter = new VehicleRegisterPresenterImp();
        vehicleInteractor.setPresenterPlugInImp(vehicleRegisterPresenter);

        VehicleRegisterFindInteractor vehicleRegisterPlugIn = new VehicleRegisterFindInteractor();
        vehicleRegisterPlugIn.setVehicleRegisterFindImp(vehicleInteractor);
        vehicleRegisterPlugIn.setVehicleRegisterPresenterImp(vehicleRegisterPresenter);

        MotorwayVignetteInteractor motorwayVignetteInteractor = new MotorwayVignetteInteractor();
        motorwayVignetteInteractor.setVehicleRegisterPlugInImp(vehicleRegisterPlugIn);

//        MotorwayVignetteStorageInterface motorwayVignetteStorage = new TollSystemPersistenceCSVStorage();
        MotorwayVignetteStorageInterface motorwayVignetteStorage = new TollSystemPersistenceSQLiteStorage();
        motorwayVignetteInteractor.setStorageImp(motorwayVignetteStorage);

        MotorwayVignettePresenterInterface motorwayVignettePresenter = new TollSystemAppPresenterImp();
        motorwayVignetteInteractor.setPresenterImp(motorwayVignettePresenter);

        TollSystemConsoleUIController consoleUIController = new TollSystemConsoleUIController();
        consoleUIController.setMotorwayVignetteImp(motorwayVignetteInteractor);

        TollSystemConsoleUIView consoleUIView = new TollSystemConsoleUIView();
        consoleUIView.setController(consoleUIController);

        consoleUIView.start();
    }
}