#include <stdlib.h>
#include <stdbool.h>
#include <stdio.h>
#include <math.h>
#include "k.h"
#include "ui.h"

bool update_doprava(struct game *game){
    bool is_moved = false;
    for (int a = 0; a < SIZE; a++){
        for (int j = SIZE - 2; j>-1; j--){
            if (game->board[a][j] != ' '){
                for(int k=j+1;k<SIZE;k++){
                    if(game->board[a][k] == ' '){
                        game->board[a][k] = game->board[a][k-1];
                        game->board[a][k-1] = ' ';
                        is_moved = true;
                    }
                }
            }
        }

        for(int j = SIZE - 1; j>0; j--){
                if(game->board[a][j] == game->board[a][j-1] && game->board[a][j] != ' '){
                    game->score += 2 << (game->board[a][j] - 64);
                    game->board[a][j]++;
                    game->board[a][j-1] = ' ';
                    is_moved = true;
                }
            }

        for (int j = SIZE - 2; j>-1; j--){
            if (game->board[a][j] != ' '){
                for(int k=j+1;k<SIZE;k++){
                    if(game->board[a][k] == ' '){
                        game->board[a][k] = game->board[a][k-1];
                        game->board[a][k-1] = ' ';
                        is_moved = true;
                    }
                }
            }
        }
    }     
    return is_moved;
}


bool update_hore(struct game *game){
    bool is_moved = false;
    for(int j=0;j<SIZE;j++){
        for(int a=1;a<SIZE;a++){
            if(game->board[a][j] != ' '){
                for(int row1=a-1;row1>=0;row1--){
                    if(game->board[row1][j] == ' '){
                        game->board[row1][j] = game->board[row1+1][j];
                        game->board[row1+1][j] = ' ';
                        is_moved = true;
                    }
                }
            }
        }

        for(int a=0;a<(SIZE-1);a++){
            if(game->board[a][j] == game->board[a+1][j]){
                if(game->board[a][j] != ' '){
                    game->board[a][j]++;
                    game->board[a+1][j] = ' ';
                    game->score += (int) pow(2, game->board[a][j] - 64);
                    is_moved = true;
                }
            }
        }

        for(int a=1;a<SIZE;a++){
            if(game->board[a][j] != ' '){
                for(int row1=a-1;row1>=0;row1--){
                    if(game->board[row1][j] == ' '){
                        game->board[row1][j] = game->board[row1+1][j];
                        game->board[row1+1][j] = ' ';
                        is_moved = true;
                    }
                }
            }
        }
    }
    return is_moved;
}

bool update_dole(struct game *game){
    bool is_moved = false;
    for(int j=0;j<SIZE;j++){
        for(int a=(SIZE-2);a>=0;a--){
            if(game->board[a][j] != ' '){
                for(int row1=a+1;row1<SIZE;row1++){
                    if(game->board[row1][j] == ' '){
                        game->board[row1][j] = game->board[row1-1][j];
                        game->board[row1-1][j] = ' ';
                        is_moved = true;
                    }
                }
            }
        }
        for(int a=(SIZE-1);a>0;a--){
            if(game->board[a][j] == game->board[a-1][j]){
                if(game->board[a][j] != ' '){
                    game->board[a][j]++;
                    game->board[a-1][j] = ' ';
                    game->score += (int) pow(2, game->board[a][j] - 64);
                    is_moved = true;
                }
            }
        }

        for(int a=(SIZE-2);a>=0;a--){
            if(game->board[a][j] != ' '){
                for(int row1=a+1;row1<SIZE;row1++){
                    if(game->board[row1][j] == ' '){
                        game->board[row1][j] = game->board[row1-1][j];
                        game->board[row1-1][j] = ' ';
                        is_moved = true;
                    }
                }
            }
        }
    }
    return is_moved;
}

bool update_dolava(struct game *game){
    bool is_moved = false;
    for (int a = 0; a < SIZE; a++){
        for (int j = 1; j<SIZE; j++){
            if (game->board[a][j] != ' '){
                for(int k=j-1;k>=0;k--){
                    if(game->board[a][k] == ' '){
                        game->board[a][k] = game->board[a][k+1];
                        game->board[a][k+1] = ' ';
                        is_moved = true;
                    }
                }
            }
        }

        for(int j = 0; j<SIZE; j++){
                if(game->board[a][j] == game->board[a][j+1] && game->board[a][j] != ' '){
                    game->score += 2 << (game->board[a][j] - 64);
                    game->board[a][j]++;
                    game->board[a][j-1] = ' ';
                    is_moved = true;
                }
            }

        for (int j = 1; j<SIZE; j++){
            if (game->board[a][j] != ' '){
                for(int k=j-1;k>=0;k--){
                    if(game->board[a][k] == ' '){
                        game->board[a][k] = game->board[a][k+1];
                        game->board[a][k+1] = ' ';
                        is_moved = true;
                    }
                }
            }
        }
    }     
    return is_moved;
}

bool is_game_won(const struct game game){
    for (int i=0 ; i<SIZE ; i++){
        for (int j =0; j <SIZE;j++){
            if (game.board[i][j] =='K'){
                return true;
            }
        }
    }
    return false;
}


bool is_move_possible(const struct game game) {
    for (int i=0 ; i<SIZE ; i++){
        if (game.board[i][0] ==' '){
            return true;
        }

        for (int j =1; j <SIZE;j++){
            if (game.board[i][j] ==' '){
                return true;
            }

            if (game.board[i][j]== game.board[i][j-1]){
                return true;
            }
        }
    }
    for (int i=0 ; i<SIZE ; i++){
        if (game.board[0][i] ==' '){
            return true;
        }

        for (int j =1; j <SIZE;j++){
            if (game.board[j][i] ==' '){
                return true;
            }

            if (game.board[j][i]== game.board[j-1][i]){
                return true;
            }
        }
    }
    return false;
}


bool update(struct game *game, int dy, int dx){
    if(dy < -1 || dy > 1) return false;
    if(dx < -1 || dx > 1) return false;
    if(dx * dy != 0) return false;
    if (dx == 1){
        return update_doprava(game);
    }
    if (dx == -1){
        return update_dolava(game);
    }
    if (dy == 1){
        return update_dole(game);
    }
    if (dy == -1){
        return update_hore(game);
    }

    return false;
}

void vypis(struct game game){
    for (int i = 0;i<SIZE;i++){
        for (int j = 0; j < SIZE; j++){
            printf("%c ",game.board[i][j]);
        }
        printf("\n");
    }
}
