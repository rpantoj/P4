package ufl.cs1.controllers;

import game.controllers.DefenderController;
import game.models.Defender;
import game.models.Attacker;
import game.models.Game;
import game.models.Node;

import java.util.List;

public final class StudentController implements DefenderController
{
	public void init(Game game) { }

	public void shutdown(Game game) { }

	public int[] update(Game game,long timeDue)
	{
		int[] actions = new int[Game.NUM_DEFENDER];
		Attacker pac = game.getAttacker();

		redDefenderDecision(game, pac, actions);
		pinkDefenderDecision(game, pac, actions);
		blueDefenderDecision(game, pac, actions);
		orangeDefenderDecision(game, pac, actions);

		return actions;

	}

	public void redDefenderDecision(Game game, Attacker pac, int[] actions) {
		Defender red = game.getDefender(0);
		int action;

		if(!red.isVulnerable())
		{
			action = red.getNextDir(pac.getLocation(), true);
		}
		else
		{
			action = red.getNextDir(pac.getLocation(), false);
		}

		actions[0] = action;
	}

	public void orangeDefenderDecision(Game game, Attacker pac, int[] actions) {
		Defender orange = game.getDefender(3);
		List<Node> powerPills = game.getCurMaze().getPowerPillNodes();
		int action;

		if(!orange.isVulnerable())
		{
			if (orange.getLocation().getPathDistance(pac.getLocation()) <
					pac.getLocation().getPathDistance(pac.getTargetNode(powerPills, true))) {
				action = orange.getNextDir(pac.getLocation(), true);
			} else {
				action = orange.getNextDir(pac.getLocation(), false);
			}
		}
		else
		{
			action = orange.getNextDir(pac.getLocation(), false);
		}

		actions[1] = action;
	}

	public void blueDefenderDecision(Game game, Attacker pac, int[] actions) {
		Defender blue = game.getDefender(2);
		int action;
		List<Node> pillNodes = game.getCurMaze().getPillNodes();

		if(!blue.isVulnerable())
		{
			if(pillNodes.size() <= 5)
			{
				action = blue.getNextDir(pillNodes.get(pillNodes.size()), true);
			}
			else
			{
				action = blue.getNextDir(pac.getLocation(), true);
			}
		}
		else
		{
			action = blue.getNextDir(pac.getLocation(), false);
		}

		actions[2] = action;
	}

	public void pinkDefenderDecision(Game game, Attacker pac, int[] actions) {
		Defender pink = game.getDefender(1);
		int action;

		if(!pink.isVulnerable())
		{
			action = pink.getNextDir(pac.getLocation().getNeighbor(pac.getReverse()), true);
			//action = pink.getNextDir(pac.getLocation(), true);
		}
		else
		{
			action = pink.getNextDir(pac.getLocation(), false);
		}

		actions[3] = action;
	}
}