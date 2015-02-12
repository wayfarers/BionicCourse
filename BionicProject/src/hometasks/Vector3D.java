package hometasks;

/**
 * Class represents vector using vector’s coordinates
 * @author Sushko
 *
 */
public class Vector3D {
	private double x;
	private double y;
	private double z;
	
	/**
	 * Creates a new vector, initialised by zero.
	 */
	public Vector3D() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	/**
	 * Creates a new vector, initialised by following parameters:
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param z - z coordinate
	 */
	public Vector3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Add a vector to current one.
	 * @param v - vector to add
	 * @return New result of sum of two Vectors
	 */
	public Vector3D add(Vector3D v) {
		double newX = x + v.x;
		double newY = y + v.y;
		double newZ = z + v.z;
		return new Vector3D(newX, newY, newZ);
	}
	
	/**
	 * Scalar product. Multiply a vector with current one.
	 * @param v - vector to multiply with
	 * @return Result of scalar product of type double
	 */
	public double multScalar(Vector3D v) {
		double newX = x * v.x;
		double newY = y * v.y;
		double newZ = z * v.z;
		return newX + newY + newZ;
	}
	
	/**
	 * Vector product. Multiply a vector with current one.
	 * @param v - vector to multiply with
	 * @return New resulting Vector3D
	 */
	public Vector3D multVector(Vector3D v) {
		double newX = y * v.z - z * v.y;
		double newY = z * v.x - x * v.z;
		double newZ = x * v.y - y * v.x;
		return new Vector3D(newX, newY, newZ);
	}
	
	/**
	 * Get a module of vector
	 * @return Module of current vector.
	 */
	public double getModule() {
		return Math.sqrt(x * x + y * y + z * z);
	}
	
	/**
	 * Returns String representation of Vector3D object.
	 */
	@Override
	public String toString() {
		return String.format("V(%.2f, %.2f, %.2f)", x, y, z);
	}
	
	/**
	 * Compare two vectors.
	 */
	@Override
	public boolean equals(Object v) {
		if (v == this) {
			return true;
		} 
		if (v instanceof Vector3D) {
			Vector3D anotherVector = (Vector3D) v;
			if (x == anotherVector.x && y == anotherVector.y && z == anotherVector.z) {
				return true;
			}
		} 
		return false;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
	
}
