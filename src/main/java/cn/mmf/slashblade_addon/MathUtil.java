package cn.mmf.slashblade_addon;

public class MathUtil
{

    public static final float[] SIN_TABLE = new float[65536];

	static
	{
        for (int i = 0; i < 65536; i++) {
            SIN_TABLE[i] = (float)Math.sin(i * Math.PI * 2.0 / 65536.0);
        }
	}

	public static float sin(float deg)
	{

		return SIN_TABLE[(int)(65536.0/360.0*deg) & 0xffff];
	}

	public static float cos(float deg)
	{
		return SIN_TABLE[((int)(65536.0/360.0*deg) + 65536/4) & 0xffff];
	}

	public static float toRadians(float angdeg)
	{
		return angdeg * (float)Math.PI / 180.0f;
	}
	
}
