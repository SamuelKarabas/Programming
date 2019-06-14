#include <stdio.h>
#include <stdlib.h>
 
struct node
{
    int key, is_different;
    struct node *left, *right;
};
  
// A utility function to create a new BST node
struct node *newNode(int item)
{
    struct node *temp =  calloc(1,sizeof(struct node));
    temp->key = item;
    temp->is_different = 1;
    temp->left = NULL;
    temp->right = NULL;
    return temp;
}
  
void inorder(struct node *root)
{
    if (root != NULL)
    {
        inorder(root->left);
        printf("%d \n", root->key);
        inorder(root->right);
    }
}
  
/* A utility function to insert a new node with given key in BST */
struct node* insert(struct node* node, int key)
{
    if (node == NULL){
        // printf("CREATING NEW NODE\n");
        return newNode(key);
    }
 
    if (key < node->key){
        node->left  = insert(node->left, key);
        // printf("SAVE TO LEFT\n");
    }else{
        // printf("SAVE TO RIGHT\n");
        node->right = insert(node->right, key);   
    }
    return node;
}

void nazov(struct node* root){
    if(root == NULL){
        return;
    }
    nazov(root->left);
    nazov(root->right);
    free(root);
}

int comp_tree(struct node* tree_1, struct node* tree_2){
    if(tree_1 == NULL){
        if(tree_2 == NULL){
            return 1;
        }
    }

    if(tree_1 != NULL){
        if(tree_2 != NULL){
            int result = comp_tree(tree_1->left, tree_2->left) && comp_tree(tree_1->right, tree_2->right);
            return result;
        }
    }

    return 0;
}
  
// Driver Program to test above functions
int main()
{
    /* Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
       20   40  60   80 */
    int num_of_trees = 0, num_of_layers = 0;
    scanf("%d %d", &num_of_trees, &num_of_layers);
    // printf("----\nN IS %d\nK IS %d\n", num_of_trees, num_of_layers);
    struct node** roots = (struct node**) calloc(num_of_trees, sizeof(struct node* ));
    // printf("LOADED BEFORE\n");

    for(int i = 0; i < num_of_trees; i++){
        for(int j = 0; j < num_of_layers; j++){
            int key = 0;
            scanf("%d", &key);
            roots[i] = insert(roots[i], key);
        }
    }
    /*root = insert(root, 50);
    insert(root, 30);
    insert(root, 20);
    insert(root, 40);
    insert(root, 70);
    insert(root, 60);
    insert(root, 80);*/
  
    for(int i = 0; i < num_of_trees; ++i){
        if(roots[i]->is_different == 0) continue;
        for(int j = i+1; j < num_of_trees; ++j){
            if(i == j) continue;
            if(comp_tree(roots[i], roots[j]) == 1){
                roots[j]->is_different = 0;
            }
        }
    }

    int num_of_different_trees = 0;
    for(int i = 0; i < num_of_trees; ++i){
        // inorder(roots[i]);
        if(roots[i]->is_different == 1){
            num_of_different_trees++;
        }
        if(roots[i] == NULL){
            continue;
        }
        nazov(roots[i]);
    }
    free(roots);
    printf("%d\n", num_of_different_trees);
    return 0;
}


/// Determine if Two Trees are Identical
/*

struct node
{
    int data;
    struct node* left;
    struct node* right;
};
 
struct node* newNode(int data)
{
    struct node* node = (struct node*)
                             malloc(sizeof(struct node));
    node->data  = data;
    node->left  = NULL;
    node->right = NULL;
 
    return(node);
}
 
int identicalTrees(struct node* a, struct node* b)
{
    if (a==NULL && b==NULL)
        return 1;
 
    if (a!=NULL && b!=NULL)
    {
        return
        (
            a->data == b->data &&
            identicalTrees(a->left, b->left) &&
            identicalTrees(a->right, b->right)
        );
    }
     
    return 0;
} 
 
int main()
{
    struct node *root1 = newNode(1);
    struct node *root2 = newNode(1);
    root1->left = newNode(2);
    root1->right = newNode(3);
    root1->left->left  = newNode(4);
    root1->left->right = newNode(5); 
 
    root2->left = newNode(2);
    root2->right = newNode(3);
    root2->left->left = newNode(4);
    root2->left->right = newNode(5); 
 
    if(identicalTrees(root1, root2))
        printf("Both tree are identical.");
    else
        printf("Trees are not identical.");
 
    getchar();
  return 0;
}

int main(){

  return 0;
}
*/
