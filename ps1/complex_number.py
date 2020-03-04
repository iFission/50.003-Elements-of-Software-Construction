import re


class ComplexNumber(object):
    real_part = 0.0
    imaginary_part = 0.0

    def __init__(self, real_part, imaginary_part):
        self.real_part = real_part
        self.imaginary_part = imaginary_part

    def __str__(self):
        # return "true"
        return f'{self.real_part} + {self.imaginary_part}i'

    def __add__(self, other):
        return ComplexNumber(self.real_part + other.real_part,
                             self.imaginary_part + other.imaginary_part)

    def __sub__(self, other):
        return ComplexNumber(self.real_part - other.real_part,
                             self.imaginary_part - other.imaginary_part)

    def __mul__(self, other):
        return ComplexNumber((self.real_part * other.real_part -
                              self.imaginary_part * other.imaginary_part),
                             (self.real_part * other.imaginary_part +
                              self.imaginary_part * other.real_part))

    def __truediv__(self, other):
        return ComplexNumber((self.real_part * other.real_part +
                              self.imaginary_part * other.imaginary_part) /
                             (other.real_part**2 + other.imaginary_part**2),
                             (self.imaginary_part * other.real_part -
                              self.real_part * other.imaginary_part) /
                             (other.real_part**2 + other.imaginary_part**2))


expression_pattern = re.compile(
    r'\(\d+(\+|\-)\d+[i]\)(\+|\-|\*|\/)\(\d+(\+|\-)\d+[i]\)')
operator_pattern = re.compile(r'\)(\+|\-|\*|\/)\(')
number_pattern = re.compile(r'\d+')

if __name__ == "__main__":
    input_string = input(
        "please input an expression to be evaluated, eg (1+2i)*(2+1i):\n")

    if expression_pattern.match(input_string) == None:
        raise (
            ValueError("please input a correct expression, eg (1+2i)*(2+1i)"))
    operator_span = operator_pattern.search(input_string).span()
    operator = input_string[operator_span[0] + 1:operator_span[1] - 1]
    numbers = number_pattern.findall(input_string)
    c1 = ComplexNumber(int(numbers[0]), int(numbers[1]))
    c2 = ComplexNumber(int(numbers[2]), int(numbers[3]))

    if operator == "+":
        print(c1 + c2)
    elif operator == "-":
        print(c1 - c2)
    elif operator == "*":
        print(c1 * c2)
    elif operator == "/":
        print(c1 / c2)
