package SBRM.CG;

public class CPLRules {
	public String expression;
	public String status;
	public double violation;
	public double support;
	public double confidence;
	public double distance;
	public boolean isnormal;

	public CPLRules() {
		this.expression = "";
		this.status = "";
		this.support = -1;
		this.violation = -1;
		this.confidence = -1;
		this.distance = 0;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getSupport() {
		return support;
	}

	public void setSupport(double support) {
		this.support = support;
	}

	public double getViolation() {
		return violation;
	}

	public void setViolation(double violation) {
		this.violation = violation;
	}

	public double getConfidence() {
		return confidence;
	}

	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public boolean isIsnormal() {
		return isnormal;
	}

	public void setIsnormal(boolean isnormal) {
		this.isnormal = isnormal;
	}

	public void printRule() {
		System.out.println("{" + this.expression + " -> Status: " + this.getStatus() + "}	(" + this.getSupport() + "/"
				+ this.getViolation() + "=" + this.getConfidence() + ")");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
