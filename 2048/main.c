#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include "k.h"
#include "hof.h"
#include "ui.h"

int main(){
	struct game hra = {
        .board = {
            {'A', ' ', ' ', 'A'},
            {' ', 'A', ' ', 'D'},
            {' ', 'A', ' ', 'C'},
            {' ', 'B', ' ', ' '}
        },
        .score = 0
    };

    render(hra);
    update(&hra, 0, 1);
    render(hra);
	return 0;
}