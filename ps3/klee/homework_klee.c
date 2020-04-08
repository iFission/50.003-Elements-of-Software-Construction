int main()
{
	int x;

	/* declare the memory region occupied by 
    * variable "x" to be symbolic */

	/* This means the program will be executed 
    * with uninstantiated, i.e., symbolic 
    * values of x */
	klee_make_symbolic(&x, sizeof(x), "x");

	/* do some computations */
	if (x < 10)
	{
		x = x + 20;
		if (x < 10)
		{
			x = -x * 10;
			if (x > 20)
			{
				x = x + 1;
			}
			else
			{
				x = x + 1;
			}
		}
		else
		{
			x = x + 1;
		}
	}
	else
	{
		x = x + 1;
	}
}
