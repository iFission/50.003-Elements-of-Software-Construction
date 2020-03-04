import pytest
from complex_number import ComplexNumber


class TestComplexNumberSimple:
    c1 = ComplexNumber(1, 1)
    c2 = ComplexNumber(0, 0)

    def test_str_simple(self):
        assert str(self.c1 * self.c2) != str(ComplexNumber(0, 0)) + " "

    def test_addition_simple(self):
        assert str(self.c1 + self.c2) == str(ComplexNumber(1, 1))

    def test_subtraction_simple(self):
        assert str(self.c1 - self.c2) == str(ComplexNumber(1, 1))

    def test_multiplication_simple(self):
        assert str(self.c1 * self.c2) == str(ComplexNumber(0, 0))

    def test_division_simple(self):
        with pytest.raises(ZeroDivisionError):
            self.c1 / self.c2


class TestComplexNumber1:
    c1 = ComplexNumber(2, 1)
    c2 = ComplexNumber(3, -2)

    def test_addition_1(self):
        assert str(self.c1 + self.c2) == str(ComplexNumber(5, -1))

    def test_subtraction_1(self):
        assert str(self.c1 - self.c2) == str(ComplexNumber(-1, 3))

    def test_multiplication_1(self):
        assert str(self.c1 * self.c2) == str(ComplexNumber(8, -1))

    def test_division_1(self):
        assert str(self.c1 / self.c2) == str(ComplexNumber(4 / 13, 7 / 13))
