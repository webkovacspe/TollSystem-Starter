package hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystemstarter;

import hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystemconsoleui.controller.TollSystemConsoleUIController;
import hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystempersistencestorage.TollSystemPersistenceStorage;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.MotorwayVignetteInteractor;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.VehicleRegisterPresenterImp;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.storage.MotorwayVignetteStorageInterface;
import hu.kovacspeterzoltan.bootcamp.vehiclepersistencestorage.PersistenceStorageCSV;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.VehicleInteractor;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.api.VehicleRegisterPresenterInterface;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.storage.VehicleRegisterStorageInterface;

public class Main {
    public static void main(String[] args) {
        MotorwayVignetteInteractor motorwayVignetteInteractor = new MotorwayVignetteInteractor();

        VehicleInteractor vehicleInteractor = new VehicleInteractor();
        VehicleRegisterStorageInterface vehicleRegisterStorage = new PersistenceStorageCSV();
        vehicleInteractor.setStorageImp(vehicleRegisterStorage);
        VehicleRegisterPresenterInterface vehicleRegisterPresenter = new VehicleRegisterPresenterImp();
        vehicleInteractor.setPresenterImp(vehicleRegisterPresenter);
        motorwayVignetteInteractor.setVehicleRegisterImp(vehicleInteractor);

        MotorwayVignetteStorageInterface motorwayVignetteStorage = new TollSystemPersistenceStorage();
        motorwayVignetteInteractor.setStorageImp(motorwayVignetteStorage);

        TollSystemConsoleUIController consoleUIController = new TollSystemConsoleUIController();
        consoleUIController.setMotorwayVignetteImp(motorwayVignetteInteractor);

        consoleUIController.start();
    }
}