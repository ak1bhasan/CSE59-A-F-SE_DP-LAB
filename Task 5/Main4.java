interface OptimizableModel {
    void quantize();
}

class ResNet18 implements OptimizableModel {
    public void quantize() {
        System.out.println("Applying int8 quantization for ResNet-18");
    }
}

class MobileNet implements OptimizableModel {
    public void quantize() {
        System.out.println("Applying dynamic quantization for MobileNet.");
    }
}

class InferenceEngine {
    public void quantizeModel(OptimizableModel model) {
        model.quantize();
    }
}

public class Main4 {
    public static void main(String[] args) {
        InferenceEngine engine = new InferenceEngine();

        OptimizableModel resNet = new ResNet18();
        OptimizableModel mobileNet = new MobileNet();

        System.out.println("AI Model Inference Engine");
        engine.quantizeModel(resNet);
        engine.quantizeModel(mobileNet);

    }
}
