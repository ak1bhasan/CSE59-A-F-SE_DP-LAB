interface INetworkProtocol {
    void broadcastMessage(String msg);
}

class BluetoothMeshNetwork implements INetworkProtocol{
    public void broadcastMessage(String msg) {
        System.out.println("Broadcasting via Bluetooth Mesh : " + msg);
    }
}

class DisasterAlertSystem {
    private INetworkProtocol network;

    public DisasterAlertSystem(INetworkProtocol network) {
        this.network = network;
    }

    public void triggerAlert(String alertData) {
        network.broadcastMessage(alertData);
    }
}

public class Main7 {
    public static void main(String[] args) {
        INetworkProtocol bluetoothNetwork = new BluetoothMeshNetwork();
        DisasterAlertSystem alertSystem = new DisasterAlertSystem(bluetoothNetwork);

        System.out.println("Disaster Alert System");
        alertSystem.triggerAlert("SEVERE WEATHER WARNING: Evacuate immediately!");
    }
}
