package Domini.Peces;

import java.util.*;
import Domini.Taulell;
import Domini.Posicio;

/**
 * Classe Rei que hereta de Peça
 *
 *
 */
public class Rei extends Peca{

    //Constructor
    public Rei(String id, Posicio pos, String color)
    {
        super(id, pos, color);
    }
    
    //Un Rei pot abançar una casella en qualsevol direcció, sempre i quan no estigui amenaçada de hake.

    public ArrayList<Posicio> moviments(Taulell t)
    {
        ArrayList<Posicio> movimentspossibles = new ArrayList();
        int x = pos.consultarX();
        int y = pos.consultarY();
        Posicio p;

        int aux_x[] = {x, x, x+1, x+1, x+1, x-1, x-1, x-1};
        int aux_y[] = {y-1, y+1, y-1, y, y+1, y-1, y, y+1};

        for(int i = 0; i < 8; i++) {
            //Si pos pes troba dins del taulell...
            p = new Posicio(aux_x[i], aux_y[i]);
            if ((p.consultarX() >= 0 && p.consultarX() < 8 && p.consultarY() >= 0 && p.consultarY() < 8))
                //Si pos p està buida o està ocupada per una peça enemiga
                if ((t.consultarCela(p) == null) || !(t.consultarCela(p).consultarColor().equals(color)))
                    movimentspossibles.add(p);
        }
        return movimentspossibles;
        //Retorna la llista de posicions on pot moure's un Rei determinat.
    }

    //Funció que vigila que el Rei no estigui en hake.
    //Comprova si hi ha alguna peça enemiga que estigui amenaçant el Rei per a un estat del taulell donat.
    public boolean hakealrey(Taulell t)
    {
        int x = pos.consultarX();
        int y = pos.consultarY();
        Posicio p;
        //Vigila un atac per davant, derrera, dreta i esquerra
        for(int i = x+1; i < 8; i++)
        {
            p = new Posicio(i, y);
            //Si pos p està buida ...
            if(t.consultarCela(p) == null) continue;

            //Si pos p està ocupada per una peça amiga ...
            else if(t.consultarCela(p).consultarColor().equals(color)) break;

            //Si pos p està ocupada per una peça enemiga ...
            else
            {
                //Si la peça enemiga és una Torre o una Reina, el Rei esta en hake
                if ((t.consultarCela(p) instanceof Torre) || (t.consultarCela(p) instanceof Reina)) return true;
                else break;
            }
        }

        for(int i = x-1; i >= 0; i--)
        {
            p = new Posicio(i, y);
            //Si pos p està buida ...
            if(t.consultarCela(p) == null) continue;

            //Si pos p està ocupada per una peça amiga ...
            else if(t.consultarCela(p).consultarColor().equals(color)) break;

            //Si pos p està ocupada per una peça enemiga ...
            else
            {
                //Si la peça enemiga és una Torre o una Reina, el Rei esta en hake
                if ((t.consultarCela(p) instanceof Torre) || (t.consultarCela(p) instanceof Reina)) return true;
                else break;
            }
        }

        for(int i = y+1; i < 8; i++)
        {
            p = new Posicio(x, i);
            //Si pos p està buida ...
            if(t.consultarCela(p) == null) continue;

            //Si pos p està ocupada per una peça amiga ...
            else if(t.consultarCela(p).consultarColor().equals(color)) break;

            //Si pos p està ocupada per una peça enemiga ...
            else
            {
                //Si la peça enemiga és una Torre o una Reina, el Rei esta en hake
                if ((t.consultarCela(p) instanceof Torre) || (t.consultarCela(p) instanceof Reina)) return true;
                else break;
            }
        }

        for(int i = y-1; i >= 0; i--)
        {
            p = new Posicio(x, i);
            //Si pos p està buida ...
            if(t.consultarCela(p) == null) continue;

            //Si posnova[x][i] està ocupada per una peça amiga ...
            else if(t.consultarCela(p).consultarColor().equals(color)) break;

            //Si pos p està ocupada per una peça enemiga ...
            else
            {
                //Si la peça enemiga és una Torre o una Reina, el Rei esta en hake
                if ((t.consultarCela(p) instanceof Torre) || (t.consultarCela(p) instanceof Reina)) return true;
                else break;
            }
        }

        //Vigila atacs diagonals
        int aux_x = x+1;
        int aux_y = y-1;
        p = new Posicio(aux_x, aux_y);

        while(p.consultarX() < 8 && p.consultarY() >= 0)
        {
            //Si pos p està buida ...
            if(t.consultarCela(p) == null)
            {
                aux_x++;
                aux_y--;
                p = new Posicio(aux_x, aux_y);
            }

            //Si pos p està ocupada per una peça amiga ...
            else if(t.consultarCela(p).consultarColor().equals(color)) break;

            //Si pos p està ocupada per una peça enemiga ...
            else
            {
                //Si la peça enemiga és una Alfil o una Reina, el Rei esta en hake
                if (t.consultarCela(p) instanceof Alfil || t.consultarCela(p) instanceof Reina) return true;
                else break;
            }
        }

        aux_x = x-1;
        aux_y = y+1;
        p = new Posicio(aux_x, aux_y);

        while(p.consultarX() >= 0 && p.consultarY() < 8)
        {
            //Si pos p està buida ...
            if(t.consultarCela(p) == null)
            {
                aux_x--;
                aux_y++;
                p = new Posicio(aux_x, aux_y);
            }

            //Si pos p està ocupada per una peça amiga ...
            else if(t.consultarCela(p).consultarColor().equals(color)) break;

            //Si pos p està ocupada per una peça enemiga ...
            else
            {
                //Si la peça enemiga és una Alfil o una Reina, el Rei esta en hake
                if (t.consultarCela(p) instanceof Alfil || t.consultarCela(p) instanceof Reina) return true;
                else break;
            }
        }

        aux_x = x-1;
        aux_y = y-1;
        p = new Posicio(aux_x, aux_y);

        while(p.consultarX() >= 0 && p.consultarY() >= 0)
        {
            //Si posnova[aux_x][aux_y] està buida ...
            if(t.consultarCela(p) == null)
            {
                aux_x--;
                aux_y--;
                p = new Posicio(aux_x, aux_y);
            }

            //Si pos p està ocupada per una peça amiga ...
            else if(t.consultarCela(p).consultarColor().equals(color)) break;

            //Si pos p està ocupada per una peça enemiga ...
            else
            {
                //Si la peça enemiga és una Alfil o una Reina, el Rei esta en hake
                if (t.consultarCela(p) instanceof Alfil || t.consultarCela(p) instanceof Reina) return true;
                else break;
            }
        }
        aux_x = x+1;
        aux_y = y+1;
        p = new Posicio(aux_x, aux_y);

        while(p.consultarX() < 8 && p.consultarY() < 8)
        {
            //Si pos p està buida ...
            if(t.consultarCela(p) == null)
            {
                aux_x++;
                aux_y++;
                p = new Posicio(aux_x, aux_y);
            }

            //Si pos p està ocupada per una peça amiga ...
            else if(t.consultarCela(p).consultarColor().equals(color)) break;

            //Si pos p està ocupada per una peça enemiga ...
            else
            {
                //Si la peça enemiga és una Alfil o una Reina, el Rei esta en hake
                if (t.consultarCela(p) instanceof Alfil || t.consultarCela(p) instanceof Reina) return true;
                else break;
            }
        }

        //Vigila atacs de Cavall
        int posx[] = { x+1, x+1, x+2, x+2, x-1, x-1, x-2, x-2};
        int posy[] = { y-2, y+2, y-1, y+1, y-2, y+2, y-1, y+1};

        for(int i = 0; i < 8; i++) {
            p = new Posicio(posx[i], posy[i]);
            //Si pos p es troba dins del taulell...
            if ((p.consultarX() >= 0 && p.consultarX() < 8 && p.consultarY() >= 0 && p.consultarY() < 8))

                //Si pos p està ocupada per un Cavall enemic ...
                if ((t.consultarCela(p) != null) && !(t.consultarCela(p).consultarColor().equals(color)) && (t.consultarCela(p) instanceof Cavall))
                    return true;
        }

        //Vigila atacs de Peo
        int pox[] = { x+1, x+1, x+1, x, x, x-1, x-1, x-1};
        int poy[] = { y-1, y+1, y, y+1, y-1, y+1, y-1, y};

        for(int i = 0; i < 8; i++)
            p = new Posicio(pox[i], poy[i]);
            //Si pos p es troba dins del taulell...
            if(p.consultarX() >= 0 && p.consultarX() < 8 && p.consultarY() >= 0 && p.consultarY() < 8){

                //Si pos p està ocupada per el Rei enemic ...
                if ((t.consultarCela(p) != null) && !(t.consultarCela(p).consultarColor().equals(color)) && (t.consultarCela(p) instanceof Rei))
                    return true;
            }

        //Si el Rei es blanc
        if(this.color.equals("blanc"))
        {
            p = new Posicio(x-1, y-1);
            //Si la pos p està ocupada per un Peo negre ...
            if(p.consultarX() > 0 && p.consultarY() > 0 && t.consultarCela(p) != null && t.consultarCela(p).consultarColor().equals("negre") && (t.consultarCela(p) instanceof Peo))
                return true;

            p = new Posicio(x-1, y+1);
            //Si la pos p està ocupada per un Peo negre ...
            if(p.consultarX() > 0 && p.consultarY() < 7 && t.consultarCela(p) != null && t.consultarCela(p).consultarColor().equals("negre") && (t.consultarCela(p) instanceof Peo))
                return true;
        }

        //Si el Rei es negre
        else
        {
            p = new Posicio(x+1, y-1);
            //Si la pos p està ocupada per un Peo blanc ...
            if(p.consultarX() < 7 && p.consultarY() > 0 && t.consultarCela(p) != null && t.consultarCela(p).consultarColor().equals("blanc") && (t.consultarCela(p) instanceof Peo))
                return true;

            p = new Posicio(x+1, y+1);
            //Si la pos p està ocupada per un Peo blanc ...
            if(p.consultarX() < 7 && p.consultarY() < 7 && t.consultarCela(p) != null && t.consultarCela(p).consultarColor().equals("blanc") && (t.consultarCela(p) instanceof Peo))
                return true;
        }
        return false;
    }
}
