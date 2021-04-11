package obj.rendering.geometry;

public class Vector3 {
    // Accuracy of comparing two double values
    private static final double COORDINATES_PRECISION = 1e-10;

    public static final Vector3 ZERO = new Vector3(0, 0, 0);
    public static final Vector3 RIGHT = new Vector3(1, 0, 0);
    public static final Vector3 UP = new Vector3(0, 1, 0);
    public static final Vector3 FORWARD = new Vector3(0, 0, 1);
    public static final Vector3 ONE = new Vector3(1, 1, 1);

    public final double x;
    public final double y;
    public final double z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(Vector3 position) {
        this.x = position.x;
        this.y = position.y;
        this.z = position.z;
    }


    public double magnitude() {
        return Math.sqrt(magnitudeSquared());
    }

    public double magnitudeSquared() {
        return x * x + y * y + z * z;
    }

    public Vector3 normalize() {
        double magnitude = magnitude();
        if (magnitude > 0) {
            double inverseMagnitude = 1 / magnitude;
            return multiply(inverseMagnitude);
        }
        return multiply(1);
    }

    public double distance(Vector3 v) {
        return subtract(v).magnitude();
    }

    public double dotProduct(Vector3 v) {
        return v.x * x + v.y * y + v.z * z;
    }

    public Vector3 crossProduct(Vector3 v) {
        return new Vector3(
                y * v.z - z * v.y,
                z * v.x - x * v.z,
                x * v.y - y * v.x
        );
    }

    public Vector3 multiply(double t) {
        return new Vector3(x * t, y * t, z * t);
    }

    public Vector3 subtract(Vector3 v) {
        return new Vector3(x - v.x, y - v.y, z - v.z);
    }

    public Vector3 add(Vector3 v) {
        return new Vector3(x + v.x, y + v.y, z + v.z);
    }

    public Vector3 normalize(Vector3 v, int xSize, int ySize) {
        double ndcPixelX = ((v.x + 0.5) / ySize);
        double ndcPixelY = ((v.y + 0.5) / xSize);
        return new Vector3(ndcPixelX, ndcPixelY, 0);
    }

    public Vector3 center(Vector3 v) {
        double screenPixelX = ((2 * v.x + 0.5) - 1);
        double screenPixelY = (1 - (2 * v.y + 0.5));
        return new Vector3(screenPixelX, screenPixelY, 0);
    }

    public Vector3 edge(Vector3 v1, Vector3 v2) {
        return new Vector3(
                v2.x - v1.x,
                v2.y - v1.y,
                v2.z - v1.z
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector3)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        Vector3 v = (Vector3) obj;
        return Math.abs(v.x - x) < COORDINATES_PRECISION
                && Math.abs(v.y - y) < COORDINATES_PRECISION
                && Math.abs(v.z - z) < COORDINATES_PRECISION;
    }

    @Override
    public int hashCode() {
        return (int) (x + y + z);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toString() {
        return x + " | " + y + " | " + z;
    }
}
