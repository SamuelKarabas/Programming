#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "hof.h"

struct player player[10];

int score_cmp(const void* player1, const void* player2){
    struct player* p1 = (struct player*) player1;
    struct player* p2 = (struct player*) player2;
    if(p1->score >= p2->score){
        return -1;
    }else{
        return 1;
    }
}

int load(struct player list[]) {
    char name[30];
    int score = 0;
    int index = 0;
    FILE *fp = fopen(HOF_FILE, "r");
    if(fp == NULL) {
        return -1;
    }
    while((fscanf(fp, "%s %d", name, &score)) != EOF){
        strcpy(list[index].name, name);
        list[index].score = score;
        index++;
    }
    fclose(fp);
    qsort(list, (size_t) index, sizeof(struct player), score_cmp);
    return index;
}

bool save(const struct player list[], const int size){
    FILE *fp = fopen(HOF_FILE, "w+");
    if( fp == NULL ){
        return false;
    }
    for(int i=0;i<size;i++){
        fprintf(fp, "%s %d\n", list[i].name, list[i].score);
    }
    fclose(fp);
    return true;
}

bool add_player(struct player list[], int* size, const struct player player){
    if (*size == 10){
        if(player.score < list[9].score){
            return false;
        }else{
            for(int i=0;i<10;i++){
                if(player.score >= list[i].score){
                    for(int j=9;j>i;j--){
                        strcpy(list[j].name, list[j-1].name);
                        list[j].score = list[j-1].score;
                    }
                    strcpy(list[i].name, player.name);
                    list[i].score = player.score;
                    return true;
                }
            }
        }
    }
    if(*size < 10 && *size > 0){
        if(player.score < list[(*size-1)].score){
            strcpy(list[*size].name, player.name);
            list[*size].score = player.score;
            (*size)++;
            return true;
        }
        for(int i=0;i<10;i++){
            if(player.score >= list[i].score){
                for(int j=(*size);j>i;j--){
                    strcpy(list[j].name, list[j-1].name);
                    list[j].score = list[j-1].score;
                }
                strcpy(list[i].name, player.name);
                list[i].score = player.score;
                (*size)++;
                return true;
            }
        }
    }
    if(*size <= 0){
        strcpy(list[0].name, player.name);
        list[0].score = player.score;
        (*size) = 1;
        return true;
    }
    return false;
}




