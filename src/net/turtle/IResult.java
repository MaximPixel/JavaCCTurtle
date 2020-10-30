package net.turtle;

public interface IResult {
	
	public static final IResult FULL_SUCCESSFUL = new IResult() {
		@Override
		public boolean isSuccessful() {
			return true;
		}
	};
	
	public static final IResult SKIP = new IResult() {
		@Override
		public boolean isSuccessful() {
			return true;
		}
		
		@Override
		public String getMessage() {
			return "Skipped";
		}
	};
	
	boolean isSuccessful();
	
	default String getMessage() {
		return "";
	}
}
