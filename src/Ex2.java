import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.Directed_WeightedGraph;
import api.Directed_WeightedGraphAlgorithms;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraph ans = null;
        Directed_WeightedGraphAlgorithms alg = new Directed_WeightedGraphAlgorithms(json_file);
        ans = alg.getGraph();
        return ans;
    }

    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = new Directed_WeightedGraphAlgorithms(new Directed_WeightedGraph());
        ans.load(json_file);
        return ans;
    }

    /**
     * This static function will run your GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        new GUI((Directed_WeightedGraphAlgorithms) alg);

    }
}