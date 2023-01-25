package hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystemstarter;

import hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystemconsoleui.controller.TollSystemConsoleUIController;
import hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystemconsoleui.presenter.TollSystemAppPresenterImp;
import hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystemconsoleui.view.TollSystemConsoleUIView;
import hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystempersistencestorage.TollSystemPersistenceStorage;
import hu.kovacspeterzoltan.bootcamp.tollsystem.vehicleregisterplugin.VehicleRegisterFindIteractor;
import hu.kovacspeterzoltan.bootcamp.tollsystem.vehicleregisterplugin.presenter.VehicleRegisterPresenterImp;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.MotorwayVignetteInteractor;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.api.MotorwayVignettePresenterInterface;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.storage.MotorwayVignetteStorageInterface;
import hu.kovacspeterzoltan.bootcamp.vehiclepersistencestorage.PersistenceStorageCSV;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.VehicleInteractor;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.api.VehicleRegisterPresenterInterface;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.storage.VehicleRegisterStorageInterface;

public class Main {
    public static void main(String[] args) {
        VehicleInteractor vehicleInteractor = new VehicleInteractor();

        VehicleRegisterStorageInterface vehicleRegisterStorage = new PersistenceStorageCSV();
        vehicleInteractor.setStorageImp(vehicleRegisterStorage);

        VehicleRegisterPresenterImp vehicleRegisterPresenter = new VehicleRegisterPresenterImp();
        vehicleInteractor.setPresenterImp(vehicleRegisterPresenter);

        VehicleRegisterFindIteractor vehicleRegisterPlugIn = new VehicleRegisterFindIteractor();
        vehicleRegisterPlugIn.setVehicleRegisterFindImp(vehicleInteractor);
        vehicleRegisterPlugIn.setVehicleRegisterPresenterImp(vehicleRegisterPresenter);

        MotorwayVignetteInteractor motorwayVignetteInteractor = new MotorwayVignetteInteractor();
        motorwayVignetteInteractor.setVehicleRegisterPlugInImp(vehicleRegisterPlugIn);

        MotorwayVignetteStorageInterface motorwayVignetteStorage = new TollSystemPersistenceStorage();
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