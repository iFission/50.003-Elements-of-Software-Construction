#!/usr/bin/env python3

import sys
import binascii
import random


def text_to_bits(text, encoding='utf-8', errors='surrogatepass'):
    bits = bin(int(binascii.hexlify(text.encode(encoding, errors)), 16))[2:]
    return bits.zfill(8 * ((len(bits) + 7) // 8))


def text_from_bits(bits, encoding='utf-8', errors='surrogatepass'):
    n = int(bits, 2)
    return int2bytes(n).decode(encoding, errors)


def int2bytes(i):
    hex_string = '%x' % i
    n = len(hex_string)
    return binascii.unhexlify(hex_string.zfill(n + (n & 1)))


class Mutator:
    def mutate(self, input_string):
        mutator_list = [
            func for func in dir(Mutator)
            if callable(getattr(Mutator, func)) and func.startswith("mutator_")
        ]
        selected_mutator_operation = random.choice(mutator_list)
        return getattr(self, selected_mutator_operation)(
            input_string), selected_mutator_operation

    def mutator_trim(self, input_string):
        int1 = random.randrange(len(input_string))
        int2 = random.randrange(len(input_string))
        if int1 > int2:
            start = int2
            end = int1
        else:
            start = int1
            end = int2
        return input_string[start:end]

    def mutator_swap(self, input_string):
        # convert string to list for mutability
        input_string = list(input_string)

        position1 = random.randrange(0, len(input_string))
        position2 = random.randrange(0, len(input_string))
        input_string[position1], input_string[position2] = input_string[
            position2], input_string[position1]
        input_string = "".join(input_string)
        return input_string

    def mutator_flip(self, input_string):
        input_string_binary = text_to_bits(input_string)
        position = random.randrange(0, len(input_string_binary))

        # convert string to list for mutability
        input_string_binary = list(input_string_binary)

        input_string_binary[position] = str(
            int(not bool(input_string_binary[position])))
        input_string = text_from_bits("".join(input_string_binary))
        return input_string


if __name__ == "__main__":
    if len(sys.argv) != 2:
        raise ValueError('input: python3 mutate_file_line.py file_path')
    with open(sys.argv[1], 'r') as f:
        line = f.readline()
        while line:
            mutated_line, mutator = Mutator().mutate(line)
            print(mutated_line)
            line = f.readline()
