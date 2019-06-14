CC=gcc
CFLAGS=-Werror -Wall -Wconversion -g
LDLIBS=-lm
FILES=k.c hof.c ui.c main.c
OBJFILES=k.o hof.o ui.o main.o
OUTPUT=game

all: $(OBJFILES)
	$(CC) $(CFLAGS) $(LDLIBS) -o $(OUTPUT) $(OBJFILES)

%.o: %.c
	$(CC) $(CFLAGS) -c $^ -o $@

cppcheck:
	cppcheck --enable=performance,unusedFunction --error-exitcode=1 *.c

clean:
	rm -rf *.o game