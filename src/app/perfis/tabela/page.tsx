"use client";

import * as React from "react";
import {
  ColumnDef,
  useReactTable,
  getCoreRowModel,
  flexRender,
} from "@tanstack/react-table";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { Card, CardHeader, CardTitle, CardDescription } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { perfisData } from "@/lib/data/index";
import { Dashboard } from "@/components/dashboard";

type Perfil = (typeof perfisData)[0];

const columns: ColumnDef<Perfil>[] = [
  {
    accessorKey: "nome",
    header: "Perfil",
    cell: ({ row }) => <div className="font-medium">{row.getValue("nome")}</div>,
    size: 160,
    enablePinning: true,
  },
  {
    accessorKey: "peso",
    header: "Peso (kg/m)",
    cell: ({ row }) => row.getValue("peso"),
    size: 110,
    enablePinning: true,
  },
  { accessorKey: "h", header: "h (mm)", size: 90 },
  { accessorKey: "b", header: "b (mm)", size: 90 },
  { accessorKey: "tw", header: "tw (mm)", size: 90 },
  { accessorKey: "tf", header: "tf (mm)", size: 90 },
  { accessorKey: "Ix", header: "Ix (cm⁴)", size: 110 },
  { accessorKey: "Wx", header: "Wx (cm³)", size: 110 },
  { accessorKey: "rx", header: "rx (cm)", size: 90 },
  { accessorKey: "Iy", header: "Iy (cm⁴)", size: 110 },
  { accessorKey: "Wy", header: "Wy (cm³)", size: 110 },
  { accessorKey: "ry", header: "ry (cm)", size: 90 },
];

function TableComponent() {
  const [filters, setFilters] = React.useState({
    minPeso: "",
    minH: "",
    minWx: "",
  });

  const handleFilterChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { id, value } = e.target;
    setFilters((prev) => ({ ...prev, [id]: value }));
  };

  // Filtragem dos dados
  const filteredData = React.useMemo(() => {
    const minPeso = parseFloat(filters.minPeso) || 0;
    const minH = parseFloat(filters.minH) || 0;
    const minWx = parseFloat(filters.minWx) || 0;

    return perfisData.filter((perfil) => {
      const pesoOk = perfil.peso >= minPeso;
      const hOk = perfil.h >= minH;
      const wxOk = (perfil.Wx || 0) >= minWx;
      return pesoOk && hOk && wxOk;
    });
  }, [filters]);

  const table = useReactTable({
    data: filteredData,
    columns,
    getCoreRowModel: getCoreRowModel(),
    enablePinning: true,
    initialState: {
      columnPinning: {
        left: ["nome", "peso"],   // Fixa as duas primeiras colunas
      },
    },
  });

  return (
    <div className="flex flex-col h-screen overflow-hidden p-1">
      {/* ==================== CARD SUPERIOR ==================== */}
      <Card className="flex-shrink-0 mb-3">
        <CardHeader className="pb-4">
          <CardTitle className="text-2xl">Tabela de Perfis W</CardTitle>
          <CardDescription className="text-xs">
            Consulte as propriedades geométricas e físicas dos perfis de aço padrão W (Gerdau/Açominas).
          </CardDescription>

          {/* Filtros */}
          <div className="grid grid-cols-3 gap-4 mt-5">
            <div>
              <Label htmlFor="minPeso">Peso ≥</Label>
              <Input
                id="minPeso"
                type="number"
                placeholder="Ex: 20"
                value={filters.minPeso}
                onChange={handleFilterChange}
                className="mt-1"
              />
              <p className="text-[10px] text-muted-foreground mt-0.5">(kg/m)</p>
            </div>
            <div>
              <Label htmlFor="minH">Altura ≥</Label>
              <Input
                id="minH"
                type="number"
                placeholder="Ex: 250"
                value={filters.minH}
                onChange={handleFilterChange}
                className="mt-1"
              />
              <p className="text-[10px] text-muted-foreground mt-0.5">(mm)</p>
            </div>
            <div>
              <Label htmlFor="minWx">Wx ≥</Label>
              <Input
                id="minWx"
                type="number"
                placeholder="Ex: 300"
                value={filters.minWx}
                onChange={handleFilterChange}
                className="mt-1"
              />
              <p className="text-[10px] text-muted-foreground mt-0.5">(cm³)</p>
            </div>
          </div>
        </CardHeader>
      </Card>

      {/* ==================== TABELA ROLÁVEL ==================== */}
      <div className="flex-1 min-h-0 flex flex-col border rounded-lg bg-background overflow-hidden">
        <div className="flex-1 min-h-0 overflow-x-auto overflow-y-auto">
          <div className="inline-block min-w-full"> {/* Permite scroll horizontal */}
            <Table>
              <TableHeader className="sticky top-0 bg-background z-50">
                {table.getHeaderGroups().map((headerGroup) => (
                  <TableRow key={headerGroup.id}>
                    {headerGroup.headers.map((header) => {
                      const isPinned = header.column.getIsPinned();
                      return (
                        <TableHead
                          key={header.id}
                          className={`font-semibold whitespace-nowrap px-4 py-3 border-r border-border ${
                            isPinned === "left"
                              ? "sticky left-0 z-50 bg-background shadow-[3px_0_8px_-3px_rgba(0,0,0,0.2)]"
                              : ""
                          }`}
                          style={{
                            width: header.getSize(),
                            minWidth: header.getSize(),
                          }}
                        >
                          {flexRender(
                            header.column.columnDef.header,
                            header.getContext()
                          )}
                        </TableHead>
                      );
                    })}
                  </TableRow>
                ))}
              </TableHeader>

              <TableBody>
                {table.getRowModel().rows.length > 0 ? (
                  table.getRowModel().rows.map((row) => (
                    <TableRow key={row.id} className="hover:bg-muted/50">
                      {row.getVisibleCells().map((cell) => {
                        const isPinned = cell.column.getIsPinned();
                        return (
                          <TableCell
                            key={cell.id}
                            className={`px-4 py-3 whitespace-nowrap border-r border-border ${
                              isPinned === "left"
                                ? "sticky left-0 z-40 bg-background shadow-[3px_0_8px_-3px_rgba(0,0,0,0.15)]"
                                : ""
                            }`}
                            style={{
                              width: cell.column.getSize(),
                              minWidth: cell.column.getSize(),
                            }}
                          >
                            {flexRender(cell.column.columnDef.cell, cell.getContext())}
                          </TableCell>
                        );
                      })}
                    </TableRow>
                  ))
                ) : (
                  <TableRow>
                    <TableCell
                      colSpan={columns.length}
                      className="h-32 text-center text-muted-foreground"
                    >
                      Nenhum perfil encontrado com os filtros aplicados.
                    </TableCell>
                  </TableRow>
                )}
              </TableBody>
            </Table>
          </div>
        </div>
      </div>
    </div>
  );
}

export default function Page() {
  return (
    <Dashboard initialCategoryId="perfis/tabela-w">
      <TableComponent />
    </Dashboard>
  );
}