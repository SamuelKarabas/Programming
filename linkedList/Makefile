CC=gcc
CFLAGS=-std=c11 -Wall -Werror -Wconversion -pedantic -lm -g
OBJS=backpack.o container.o command.o game.o item.o parser.o room.o world.o

all: ps4 

ps4: $(OBJS) main.c
	$(CC) $(CFLAGS) $^ -o $@

%.o: %.c
	$(CC) $(CFLAGS) $< -c

%: %.o
	$(CC) $(CFLAGS) 

clean:
	rm -f *.o ps4

valgrind: ps4
	valgrind --leak-check=full ./$^ < scenario.txt

cppcheck:
	cppcheck --enable=warning,performance,unusedFunction --error-exitcode=1 *.c
