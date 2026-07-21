class ModelWeightsCache {
    private static volatile ModelWeightsCache instance;

    private ModelWeightsCache() {
        System.out.println("Loading 2GB model weights into memory... (cached successfully)");
    }

    public static ModelWeightsCache getInstance() {
        if (instance == null) {
            synchronized (ModelWeightsCache.class) {
                if (instance == null) {
                    instance = new ModelWeightsCache();
                }
            }
        }
        return instance;
    }

    public void predict(String dataSample) {
        System.out.println("Running inference on : " + dataSample);
    }
 
}


public class Ex5 {
    public static void main(String[] args) {
        ModelWeightsCache cache1 = ModelWeightsCache.getInstance();
        cache1.predict("image_01.png");

        ModelWeightsCache cache2 = ModelWeightsCache.getInstance();
        cache1.predict("image_02.png");
    }
}
