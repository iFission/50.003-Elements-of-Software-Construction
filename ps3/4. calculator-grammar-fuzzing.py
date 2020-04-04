import random


# def fuzzer(Expr):
def fuzz_expr(Expr):
    return random.choice(["Expr + Term", "Expr - Term", "Term"])


def fuzz_term(Term):
    return random.choice(["Term * Factor", "Term / Factor", "Factor"])


def fuzz_factor(Factor):
    return random.choice(["-Integer", "Expr", "Integer", "Integer.Integer"])


def fuzz_integer(Integer):
    return random.choice(["Digit", "IntegerDigit"])


def fuzz_digit(Digit):
    return str(random.choice([i for i in range(1, 11)]))


def fuzzer():
    output = "Expr"
    while "Expr" in output:
        output = fuzz_expr(output)
        while "Term" in output:
            output = output.replace("Term", fuzz_term("Term"), 1)
        while "Factor" in output:
            output = output.replace("Factor", fuzz_factor("Factor"), 1)
    while "Integer" in output:
        output = output.replace("Integer", fuzz_integer("Integer"), 1)
    while "Digit" in output:
        output = output.replace("Digit", fuzz_digit("Digit"), 1)
    return output


random.seed(7)

print(fuzzer())
print(fuzzer())
print(fuzzer())
print(fuzzer())
print(fuzzer())
print(fuzzer())