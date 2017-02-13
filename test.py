from pyeda.inter import *
import sys

def main(argv):

	with open(argv[1]) as fout:
		phi_out = fout.readline().replace("V", "|").replace("^", "&")

	with open(argv[2]) as fref:
		phi_ref = fref.readline().replace("V", "|").replace("^", "&")

	#	construct boolean formulas
	phi_ref = expr(phi_ref)
	phi_out = expr(phi_out)

	#	try to satisfy the formulas
	sat_ref = phi_ref.satisfy_one()
	sat_out = phi_out.satisfy_one()

	#	None = not-satisfiable
	#	~None = satisfiable
	eq = ((sat_ref == None) and (sat_out == None))  or \
		 ((sat_ref != None) and (sat_out != None))

	if(eq):
		print("1")
	else:
		print("0")


if __name__ == '__main__':
	main(sys.argv)