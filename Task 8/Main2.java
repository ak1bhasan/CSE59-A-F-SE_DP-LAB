class NeuralNetConfig {
    private final int hiddenLayers;
    private final double LearningRate;
    private final String optimizer;
    private final int epochs;

    private NeuralNetConfig(Builder builder) {
        this.hiddenLayers = builder.hiddenLayers;
        this.LearningRate = builder.learningRate;
        this.optimizer = builder.optimizer;
        this.epochs = builder.epochs;
    }

    public void printSummary() {
        System.out.println("Model : " + hiddenLayers + "layers, " + optimizer + ", Epochs : " + epochs);
    }

    public static class Builder {
        private int hiddenLayers = 1;
        private double learningRate = 0.01;
        private String optimizer = "SGD";
        private int epochs = 10;

        public Builder setHiddenLayers(int hiddenLayers) {
            this.hiddenLayers = hiddenLayers; 
            return this;
        }

        public Builder setLearningRate(double learningRate) {
            this.learningRate = learningRate; 
            return this;
        }

        public Builder setOptimizer(String optimizer) {  
            this.optimizer = optimizer; 
            return this;
        }

        public Builder setEpochs(int epochs) { 
            this.epochs = epochs; 
            return this;
         }

        public NeuralNetConfig build() { 
            return new NeuralNetConfig(this);
        }
    }
}

public class Ex2 {
    public static void main(String[] args) {
        NeuralNetConfig config = new NeuralNetConfig.Builder().setHiddenLayers(5)
                                .setOptimizer("Adam")
                                .build();
        config.printSummary();
    }
}
