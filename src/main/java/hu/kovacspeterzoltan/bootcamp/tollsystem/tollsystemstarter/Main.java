package hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystemstarter;

import hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystemconsoleui.controller.TollSystemConsoleUIController;
import hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystemconsoleui.dto.TollSystemAppPresenterImp;
import hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystempersistencestorage.TollSystemPersistenceStorage;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.MotorwayVignetteInteractor;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.api.MotorwayVignettePresenterInterface;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.storage.MotorwayVignetteStorageInterface;
import hu.kovacspeterzoltan.bootcamp.vehiclepersistencestorage.PersistenceStorageCSV;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.VehicleInteractor;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.storage.VehicleRegisterStorageInterface;

public class Main {
    public static void main(String[] args) {
        MotorwayVignetteInteractor motorwayVignetteInteractor = new MotorwayVignetteInteractor();

        VehicleInteractor vehicleInteractor = new VehicleInteractor();
        VehicleRegisterStorageInterface vehicleRegisterStorage = new PersistenceStorageCSV();
        vehicleInteractor.setStorageImp(vehicleRegisterStorage);
        vehicleInteractor.setPresenterImp(motorwayVignetteInteractor);
        motorwayVignetteInteractor.setVehicleRegisterImp(vehicleInteractor);

        MotorwayVignetteStorageInterface motorwayVignetteStorage = new TollSystemPersistenceStorage();
        motorwayVignetteInteractor.setStorageImp(motorwayVignetteStorage);

        MotorwayVignettePresenterInterface motorwayVignettePresenter = new TollSystemAppPresenterImp();
        motorwayVignetteInteractor.setPresenterImp(motorwayVignettePresenter);

        TollSystemConsoleUIController consoleUIController = new TollSystemConsoleUIController();
        consoleUIController.setMotorwayVignetteImp(motorwayVignetteInteractor);

        consoleUIController.start();
    }
}