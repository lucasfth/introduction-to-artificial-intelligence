from io import StringIO
import sys


def print_state(step, pegs, source, destination):
    print(f"\n{step}. Move disk from {source} to {destination}")
    for peg in ['A', 'B', 'C']:
        print(f"{peg}: {' '.join(str(disk) for disk in pegs[peg])}")


def move_disk(pegs, source, destination):
    disk = pegs[source].pop()
    pegs[destination].append(disk)


def move_tower(n, source, destination, auxiliary, pegs, step=[0]):
    # print(f"\nSrc={source}, Dst={destination}, Aux={auxiliary}")
    if n == 1:
        step[0] += 1
        move_disk(pegs, source, destination)
        print_state(step[0], pegs, source, destination)
    else:
        move_tower(n-1, source, auxiliary, destination, pegs, step)
        step[0] += 1
        move_disk(pegs, source, destination)
        print_state(step[0], pegs, source, destination)
        move_tower(n-1, auxiliary, destination, source, pegs, step)


# Initial setup
n = 4
pegs = {'A': list(range(n, 0, -1)), 'B': [], 'C': []}

# Capture the output in a more structured way rather than printing directly
old_stdout = sys.stdout
sys.stdout = mystdout = StringIO()

# Run the function
move_tower(n, 'A', 'C', 'B', pegs)

# Restore stdout
sys.stdout = old_stdout

# Get the captured output
output = mystdout.getvalue()

print(output)  # Return the output directly for demonstration
