import { Tabs } from 'expo-router';

export default function TabLayout() {
  return (
    <Tabs>
      <Tabs.Screen name="team" options={{ title: "Mein Team" }} />
      <Tabs.Screen name="market" options={{ title: "Transfermarkt" }} />
      <Tabs.Screen name="info" options={{ title: "Infos" }} />
      <Tabs.Screen name="league" options={{ title: "Liga" }} />
    </Tabs>
  );
}
